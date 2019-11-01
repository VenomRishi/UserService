/******************************************************************************
 *  Purpose: This is class of User Service class in the @Service it handles
 *  		 all the request coming from controller and which is then process
 *  		 in service class as it is class so there is only definition of
 *  		 method which is declare in interface UserService
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   18-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.user.configuration.UserConfiguration;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.dto.SetPasswordDTO;
import com.bridgelabz.fundoo.user.exception.custom.ForgotPasswordException;
import com.bridgelabz.fundoo.user.exception.custom.LoginException;
import com.bridgelabz.fundoo.user.exception.custom.RegisterException;
import com.bridgelabz.fundoo.user.exception.custom.RegisterVerifyException;
import com.bridgelabz.fundoo.user.exception.custom.SetPasswordException;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.user.response.Response;
import com.bridgelabz.fundoo.user.utility.Constant;
import com.bridgelabz.fundoo.user.utility.TokenUtility;
import com.bridgelabz.fundoo.user.utility.UserUtility;


import io.jsonwebtoken.Claims;

@Service
public class ImplUserService implements IUserService {

	private static final Logger LOG = LoggerFactory.getLogger(ImplUserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConfiguration config;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private UserUtility userUtility;

	/**
	 * Purpose: method for login the user into the system
	 * 
	 * @param loginDTO this is object of RegisterDTO class which is passed from
	 *                 controller this object is holding all the information which
	 *                 is coming from the user end
	 * 
	 * @return returns Response which contains the response of the method
	 */
	@Override
	public Response login(LoginDTO loginDTO) {
		LOG.info(Constant.SERVICE_LOGIN_METHOD);
		int key;
		if (userRepository.findAll().stream().anyMatch(i -> i.getEmail().equals(loginDTO.getEmail())
				&& config.passwordEncoder().matches(loginDTO.getPassword(), i.getPassword()) && i.isActive())) {
			key = userRepository.findByEmail(loginDTO.getEmail()).get().getUid();
			LOG.info(Constant.SUCCESS_LOGIN);
			return new Response(200, Constant.SUCCESS_LOGIN,
					TokenUtility.buildToken(String.valueOf(key), Constant.KEY_LOGIN));
		}

		else {
			LOG.error(Constant.FAILED_LOGIN);
			throw new LoginException(Constant.FAILED_LOGIN);

		}

	}

	/**
	 * Purpose: method for checking the email is there in database or not
	 * 
	 * @param input from program
	 * 
	 * @return true if email is found in database or else return false
	 */
	// @Override
	// public boolean validateEmail(String email) {
	// return userRepository.findAll().stream().anyMatch(i ->
	// i.getEmail().equals(email));
	// }

	/**
	 * Purpose: method for registration of new user
	 * 
	 * @param registerDTO this is object of RegisterDTO class which is passed from
	 *                    controller this object is holding all the information
	 *                    which is coming from the user end
	 * @return returns Response which contains the response of the method
	 */
	@Override
	public Response register(RegisterDTO registerDTO) {

		LOG.info(Constant.SERVICE_REGISTER_METHOD);
		// System.out.println(userRepository.findByEmail(registerDTO.getEmail()));
		if (!userRepository.findByEmail(registerDTO.getEmail()).isEmpty()) {
			LOG.error(registerDTO.getEmail() + Constant.REGISTER_EMAIL_FOUND);
			throw new RegisterException(registerDTO.getEmail() + Constant.REGISTER_EMAIL_FOUND);
		}

		registerDTO.setPassword(config.passwordEncoder().encode(registerDTO.getPassword()));
		User user = config.modelMapper().map(registerDTO, User.class);
		registerVerificationSendEmail(registerDTO.getEmail());
		LOG.info(Constant.SUCCESS_REGISTER);
		return new Response(200, Constant.SUCCESS_REGISTER, userRepository.save(user));
	}

	/**
	 * Purpose: method is created for sending the email for activating the user
	 * account user only login to account only if user account is activated if the
	 * user wants to activate the account then he/she has to verify the email
	 * verification link
	 * 
	 * @param email from where the user wants to send email this parameter comes
	 *              from the user registration form
	 */
	@Override
	public void registerVerificationSendEmail(String email) {
		LOG.info(Constant.SERVICE_REGISTER_VERIFICATION_METHOD);

		// code for sending email to recipient
		String token = TokenUtility.buildToken(email, Constant.KEY_REGISTER_VERIFY);
		SimpleMailMessage sampleMailMessage = userUtility.sendMailForRegistrationVerification(email, token);
		javaMailSender.send(sampleMailMessage);
		LOG.info(Constant.EMAIL_SEND);

	}

	/**
	 * Purpose: method for verification account when new user register themselve's
	 * then the system generated mail is send to that particular user and when user
	 * goes to that mail and click the verification link then user account gets
	 * activated when user account is activated user is getting authorised to use
	 * there application then user can easy login with there email account with the
	 * password along with it
	 * 
	 * @param token this is token coming from the mail which is send while
	 *              registration to user mail account in that mail token is
	 *              available
	 * @return returns Response which contains the response of the method
	 */
	@Override
	public Response verify(String token) {
		LOG.info(Constant.SERVICE_VERIFY_USER_METHOD);

		Claims claims = TokenUtility.parseToken(token, Constant.KEY_REGISTER_VERIFY);
		User user = userRepository.findAll().stream().filter(i -> i.getEmail().equals(claims.getSubject())).findAny()
				.orElse(null);
		if (user == null) {
			LOG.error(Constant.FAILED_TO_VERIFY);
			throw new RegisterVerifyException(Constant.FAILED_TO_VERIFY);
		}

		user.setActive(true);
		LOG.info(Constant.SUCCESS_VERIFY);
		return new Response(200, Constant.SUCCESS_VERIFY, userRepository.save(user));

	}

	/**
	 * Purpose: method is created for the sending the set password link on email if
	 * user forgets there password
	 * 
	 * @param email email id receives from the use from user response
	 * 
	 * @return returns Response which contains the response of the method
	 */
	@Override
	public Response forgotPassword(String email) {
		LOG.info(Constant.SERVICE_FORGOT_PASSWORD_METHOD);

		// check email is there in database or not
		if (userRepository.findByEmail(email) == null) {
			LOG.info(email + Constant.EMAIL_NOT_FOUND);
			throw new ForgotPasswordException(email + Constant.EMAIL_NOT_FOUND);

		}
		// code for sending email to recipient
		String token = TokenUtility.buildToken(email, Constant.KEY_SET_PASSWORD);
		SimpleMailMessage sampleMailMessage = userUtility.sendMail(email, token);
		javaMailSender.send(sampleMailMessage);
		LOG.info(Constant.EMAIL_SEND_FOR_FORGOT_PASSWORD);
		return new Response(200, Constant.EMAIL_SEND_FOR_FORGOT_PASSWORD, null);

	}

	/**
	 * Purpose: method is created for changing the password of current user
	 * 
	 * @param setPasswordDTO input from user
	 * 
	 * @return returns Response which contains the response of the method
	 */
	@Override
	public Response setPassword(SetPasswordDTO setPasswordDTO) {
		LOG.info(Constant.SERVICE_SET_PASSWORD_METHOD);

		Claims claims = TokenUtility.parseToken(setPasswordDTO.getToken(), Constant.KEY_SET_PASSWORD);
		LOG.info(claims.getSubject());
		User user = userRepository.findAll().stream().filter(i -> i.getEmail().equals(claims.getSubject())).findAny()
				.orElse(null);
		if (user == null) {

			LOG.error(Constant.FAILED_TO_SET_PASSWORD);
			throw new SetPasswordException(Constant.FAILED_TO_SET_PASSWORD);

		}
		user.setPassword(config.passwordEncoder().encode(setPasswordDTO.getPassword()));
		LOG.info(Constant.SUCCESS_SET_PASSWORD);
		return new Response(200, Constant.SUCCESS_SET_PASSWORD, userRepository.save(user));

	}

}
