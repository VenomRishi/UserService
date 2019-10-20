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

import java.util.Date;

import org.modelmapper.spi.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundoo.user.configuration.UserConfiguration;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.exception.DefaultExceptionHandling;
import com.bridgelabz.fundoo.user.exception.UserException;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.user.utility.UserUtility;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

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
	 * @return returns true if user get login into the system successfully else
	 *         returns false
	 */
	@Override
	public String login(LoginDTO loginDTO) {
		LOG.info("method: login service");
		try {
			System.out.println(userRepository.findById(1) != null);
			if (userRepository.findAll().stream().anyMatch(i -> i.getEmail().equals(loginDTO.getEmail())
					&& config.passwordEncoder().matches(loginDTO.getPassword(), i.getPassword()) && i.isActive())) {
				LOG.info("login successful");
				return "Login successful";

			}

			else {
				LOG.error("Something went wrong");
				throw new UserException("Something went wrong");

			}

		} catch (Exception ex) {

			ResponseEntity<ErrorMessage> msg = new DefaultExceptionHandling().somethingWentWrong(ex);
			LOG.error(msg.toString());
			throw new UserException(msg.toString());
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
	 * @return returns true if user register successfully else application will
	 *         throw the exception
	 */
	@Override
	public User register(RegisterDTO registerDTO) {
		try {
			LOG.info("method: register service");
			// System.out.println(userRepository.findByEmail(registerDTO.getEmail()));
			if (userRepository.findByEmail(registerDTO.getEmail()).isEmpty()) {
				registerVerificationSendEmail(registerDTO.getEmail());
				registerDTO.setPassword(config.passwordEncoder().encode(registerDTO.getPassword()));
				User user = config.modelMapper().map(registerDTO, User.class);
				LOG.info("registration successful");
				return userRepository.save(user);

			} else {
				LOG.error(registerDTO.getEmail()
						+ " found record with this email cannot able to create new entry with this email");
				throw new UserException(registerDTO.getEmail()
						+ " found record with this email cannot able to create new entry with this email");

			}
		} catch (Exception ex) {
			ResponseEntity<ErrorMessage> msg = new DefaultExceptionHandling().somethingWentWrong(ex);
			LOG.error(msg.toString());
			throw new UserException(msg.toString());
		}

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
		LOG.info("method: register verification sending email");
		try {
			// code for sending email to recipient
			String token = Jwts.builder().setSubject(email).setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, "verifykey").compact();
			SimpleMailMessage sampleMailMessage = userUtility.sendMailForRegistrationVerification(email, token);
			javaMailSender.send(sampleMailMessage);
			LOG.info("email is send to recipient");
		} catch (Exception ex) {
			ResponseEntity<ErrorMessage> msg = new DefaultExceptionHandling().somethingWentWrong(ex);
			LOG.error(msg.toString());
			throw new UserException(msg.toString());
		}
	}

	/**
	 * Purpose: method for verification account when new user register themselve's
	 * then the system generated mail is send to that particular user and when user
	 * goes to that mail and click the verification link then user account gets
	 * activated when user account is activated user is getting authorized to use
	 * there application then user can easy login with there email account with the
	 * password along with it
	 * 
	 * @param token this is token coming from the mail which is send while
	 *              registration to user mail account in that mail token is
	 *              available
	 * @return if user successfully verify the link then it will return the user
	 *         back else application will throw the exception failed to verify email
	 */
	@Override
	public User verify(String token) {
		LOG.info("method: verify service");
		try {
			Claims claims = Jwts.parser().setSigningKey("verifykey").parseClaimsJws(token).getBody();
			User user = userRepository.findAll().stream().filter(i -> i.getEmail().equals(claims.getSubject()))
					.findAny().orElse(null);
			if (user != null) {
				user.setActive(true);
				LOG.info("user updated");
				return userRepository.save(user);

			} else {
				LOG.error("failed to verify..!");
				throw new UserException("failed to verify..!");
			}

		} catch (Exception ex) {
			ResponseEntity<ErrorMessage> msg = new DefaultExceptionHandling().somethingWentWrong(ex);
			LOG.error(msg.toString());
			throw new UserException(msg.toString());
		}

	}

	/**
	 * Purpose: method is created for the sending the set password link on email if
	 * user forgets there password
	 * 
	 * @param email email id receives from the use from user response
	 * 
	 * @return returns the string saying that email send for forgot password link
	 */
	@Override
	public String forgotPassword(String email) {
		LOG.info("method: forgot password service");
		try {
			// check email is there in database or not
			if (userRepository.findByEmail(email) != null) {
				// code for sending email to recipient
				String token = Jwts.builder().setSubject(email).setIssuedAt(new Date())
						.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
				SimpleMailMessage sampleMailMessage = userUtility.sendMail(email, token);
				javaMailSender.send(sampleMailMessage);
				LOG.info("Email is send for forget password click on link to send new password");
				return "Email is send for forget password click on link to send new password";
			} else {
				LOG.info(email + " not found is database");
				throw new UserException(email + " not found is database");
			}

		} catch (Exception ex) {
			ResponseEntity<ErrorMessage> msg = new DefaultExceptionHandling().somethingWentWrong(ex);
			LOG.error(msg.toString());
			throw new UserException(msg.toString());
		}
	}

	/**
	 * Purpose: method is created for changing the password of current user
	 * 
	 * @param password input from user
	 * @param token    input from user URL(unified resource locator)
	 * 
	 * @return returns the user object which is updated
	 */
	@Override
	public User setPassword(String password, String token) {
		LOG.info("method: set password service");
		try {
			Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
			LOG.info(claims.getSubject());
			User user = userRepository.findAll().stream().filter(i -> i.getEmail().equals(claims.getSubject()))
					.findAny().orElse(null);
			if (user != null) {
				user.setPassword(config.passwordEncoder().encode(password));
				LOG.info("user updated");
				return userRepository.save(user);
			} else {
				LOG.error("unable to set new password due to there's not email associated with your token");
				throw new UserException(
						"unable to set new password due to there's not email associated with your token");
			}

		} catch (Exception ex) {
			ResponseEntity<ErrorMessage> msg = new DefaultExceptionHandling().somethingWentWrong(ex);
			LOG.error(msg.toString());
			throw new UserException(msg.toString());
		}

	}

}
