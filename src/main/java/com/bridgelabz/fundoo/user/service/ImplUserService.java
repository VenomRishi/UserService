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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.bridgelabz.fundoo.user.configuration.UserConfiguration;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.dto.SetPasswordDTO;
import com.bridgelabz.fundoo.user.entity.RabbitMQBody;
import com.bridgelabz.fundoo.user.entity.User;
import com.bridgelabz.fundoo.user.exception.custom.ForgotPasswordException;
import com.bridgelabz.fundoo.user.exception.custom.LoginException;
import com.bridgelabz.fundoo.user.exception.custom.RegisterException;
import com.bridgelabz.fundoo.user.exception.custom.RegisterVerifyException;
import com.bridgelabz.fundoo.user.exception.custom.SetPasswordException;
import com.bridgelabz.fundoo.user.exception.custom.UserException;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.user.response.Response;
import com.bridgelabz.fundoo.user.response.UserToken;
import com.bridgelabz.fundoo.user.utility.Constant;
import com.bridgelabz.fundoo.user.utility.TokenUtility;

@Service
public class ImplUserService implements IUserService {

	private static final Logger LOG = LoggerFactory.getLogger(ImplUserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConfiguration config;

	@Autowired
	private RabbitTemplate rabbitTemplate;

//	@Autowired
//	private ServletContext context;

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
		User user;
		if (userRepository.findAll().stream().anyMatch(i -> i.getEmail().equals(loginDTO.getEmail())
				&& config.passwordEncoder().matches(loginDTO.getPassword(), i.getPassword()) && i.isActive())) {
			user = userRepository.findByEmail(loginDTO.getEmail()).get();
			LOG.info(Constant.SUCCESS_LOGIN);
			return new Response(200, Constant.SUCCESS_LOGIN,
					new UserToken(TokenUtility.buildToken(String.valueOf(user.getUid()), Constant.KEY_LOGIN),
							TokenUtility.buildToken(String.valueOf(user.getEmail()), Constant.KEY_LOGIN)));
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
		String token = TokenUtility.buildToken(registerDTO.getEmail(), Constant.KEY_REGISTER_VERIFY);
		// code for sending email in RABBITMQ queue

		RabbitMQBody rabbitMQBody = new RabbitMQBody();
		rabbitMQBody.setEmail(registerDTO.getEmail());
		rabbitMQBody.setSubject(Constant.EMAIL_SUBJECT_VERIFY);
		rabbitMQBody.setBody(Constant.BASE_URL + Constant.VERIFY_URI + "?token=" + token);

		rabbitTemplate.convertAndSend(Constant.ROUTING_KEY, rabbitMQBody);
		// registerVerificationSendEmail(registerDTO.getEmail());
		LOG.info(Constant.SUCCESS_REGISTER);
		return new Response(200, Constant.SUCCESS_REGISTER, userRepository.save(user));
	}

	/**
	 * Purpose: method for verification account when new user register themselve's
	 * then the system generated mail is send to that particular user and when user
	 * goes to that mail and click the verification link then user account gets
	 * activated when user account is activated user is getting authorised to use
	 * there application then user can easy login with there email account with the
	 * password along with it
	 * 
	 * @param userId this is token coming from the mail which is send while
	 *              registration to user mail account in that mail token is
	 *              available
	 * @return returns user entity
	 */
	@CachePut(value = "user", key = "#userId")
	@Override
	public User verify(String userId) {
		LOG.info(Constant.SERVICE_VERIFY_USER_METHOD);
		User user = userRepository.findAll().stream().filter(i -> i.getEmail().equals(userId)).findAny()
				.orElse(null);
		if (user == null) {
			LOG.error(Constant.FAILED_TO_VERIFY);
			throw new RegisterVerifyException(Constant.FAILED_TO_VERIFY);
		}

		user.setActive(true);
		LOG.info(Constant.SUCCESS_VERIFY);
		return userRepository.save(user);

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

		RabbitMQBody rabbitMQBody = new RabbitMQBody();
		rabbitMQBody.setEmail(email);
		rabbitMQBody.setSubject(Constant.EMAIL_SUBJECT_SETPASSWORD);
		rabbitMQBody.setBody(Constant.BASE_URL + Constant.SET_URI + "?token=" + token);

		rabbitTemplate.convertAndSend(Constant.ROUTING_KEY, rabbitMQBody);

		LOG.info(Constant.EMAIL_SEND_FOR_FORGOT_PASSWORD);
		return new Response(200, Constant.EMAIL_SEND_FOR_FORGOT_PASSWORD, null);

	}

	/**
	 * Purpose: method is created for changing the password of current user
	 * 
	 * @param setPasswordDTO input from user
	 * 
	 * @return returns user entity
	 */
	@CachePut(value = "user", key = "#userId")
	@Override
	public User setPassword(String userId, SetPasswordDTO setPasswordDTO) {
		LOG.info(Constant.SERVICE_SET_PASSWORD_METHOD);

		
		User user = userRepository.findAll().stream().filter(i -> i.getEmail().equals(userId)).findAny()
				.orElse(null);
		if (user == null) {

			LOG.error(Constant.FAILED_TO_SET_PASSWORD);
			throw new SetPasswordException(Constant.FAILED_TO_SET_PASSWORD);

		}
		user.setPassword(config.passwordEncoder().encode(setPasswordDTO.getPassword()));
		LOG.info(Constant.SUCCESS_SET_PASSWORD);
		return userRepository.save(user);

	}

	@Override
	public Response getProfile(String token) {
		LOG.info(Constant.SERVICE_GET_UPLOAD_PROFILE);
		String email = TokenUtility.parseToken(token, Constant.KEY_LOGIN).getSubject();
		Optional<User> user = userRepository.findByEmail(email);
		if (!user.isPresent()) {
			throw new UserException(Constant.EMAIL_NOT_FOUND);
		}

		String images="";
		String filePath = Constant.UPLOAD_FOLDER;
		File fileFolder = new File(filePath);
		if (fileFolder != null) {
			for(final File file: fileFolder.listFiles()) {
				if(!file.isDirectory()) {
					String encodeBase64 = null;
					try {
						if((Constant.UPLOAD_FOLDER+file.getName()).equals(user.get().getProfile())){
							String extension= FilenameUtils.getExtension(file.getName());
							FileInputStream fileInputStream  =new FileInputStream(file);
							byte[] bytes=new byte[(int)file.length()];
							fileInputStream.read(bytes);
							encodeBase64= Base64.getEncoder().encodeToString(bytes);
							images=("data:image/"+extension+";base64,"+encodeBase64);
							fileInputStream.close();
							break;
						}
						
 					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}
		return new Response(200,Constant.GET_IMAGES_RESPONSE, images);
	}

	/**
	 * Purpose: this method is used to update upload the user profile picture
	 * 
	 * @param image this is MultipartFile coming from the user end
	 * 
	 * @param userId this parameter helps to specify on which user needs to set the
	 *              profile picture
	 * 
	 * @return returns user entity
	 */
	@CachePut(value = "user", key = "#userId")
	@Override
	public User updateProfile(MultipartFile image, String userId) throws Exception {
		LOG.info(Constant.SERVICE_UPDATE_UPLOAD_PROFILE);
		User user = userRepository.findByEmail(userId).orElse(null);
		if (user == null) {
			LOG.info(userId + Constant.EMAIL_NOT_FOUND);
			throw new ForgotPasswordException(userId + Constant.EMAIL_NOT_FOUND);
		}

		if (image != null && image.getContentType() != null
				&& !image.getContentType().toLowerCase().startsWith("image"))
			throw new UserException(Constant.IMAGE_FORMAT_EXCEPTION);

		byte[] bytes = image.getBytes();
		String extension = image.getContentType().replace("image/", "");
		String fileLocation = Constant.UPLOAD_FOLDER + userId + "." + extension;
		Path path = Paths.get(fileLocation);
		Files.write(path, bytes);
		user.setProfile(fileLocation);
		return userRepository.save(user);
	}

	/**
	 * Purpose: this method is used to delete profile picture from the user profile
	 * picture
	 * 
	 * @param userId this parameter helps to specify on which user needs to set the
	 *              profile picture
	 * 
	 * @return returns user entity
	 * @throws IOException
	 */
	@CachePut(value = "user", key = "#userId")
	@Override
	public User deleteProfile(String userId) throws IOException {
		LOG.info(Constant.SERVICE_DELETE_UPLOAD_PROFILE);
		User user = userRepository.findByEmail(userId).orElse(null);
		if (user == null) {
			LOG.info(userId + Constant.EMAIL_NOT_FOUND);
			throw new UserException(userId + Constant.EMAIL_NOT_FOUND);
		}
		Path path = Paths.get(user.getProfile());
		Files.delete(path);
		user.setProfile(null);
		return userRepository.save(user);
	}
	
	@Override
	public Response getAllUsers() {
		return new Response(200, Constant.UPLOAD_SUCCESS,
				userRepository.findAll().stream().collect(Collectors.toList()));
	}
	
	@Cacheable(value = "user", key = "#userId")
	@Override
	public User getUser(String userId) {
		System.out.println("In method"+userId);
		return userRepository.findByEmail(userId).get();
	}

}
