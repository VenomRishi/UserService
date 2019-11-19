/******************************************************************************
 *  Purpose: Class is implemented for handling the request coming from the user
 *  		 @RestController defines that it will deal with the rest controller
 *  		 @RequestMapping will handle the request
 *  		 in this class handling request related user
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   18-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.dto.SetPasswordDTO;
import com.bridgelabz.fundoo.user.response.Response;
import com.bridgelabz.fundoo.user.service.IUserService;
import com.bridgelabz.fundoo.user.utility.Constant;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private IUserService service;

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	/**
	 * Purpose: this API(application programming interface) is created for
	 * registering new user into system
	 * 
	 * @param registerDTO response coming from front end will going to handle in
	 *                    this controller class as this request is register request
	 *                    so we are mapping this request into RegisterDTO POJO class
	 *                    by using @RequestBody and then giving DTO to service to
	 *                    process
	 * @return returns ResponseEntity which is holding the User object and
	 *         HttpStatus in that entity
	 */
	@PostMapping("/register")
	public ResponseEntity<Response> register(@Valid @RequestBody RegisterDTO registerDTO) {
		LOG.info(Constant.CONTROLLER_REGISTER_METHOD);
		return new ResponseEntity<>(service.register(registerDTO), HttpStatus.OK);
	}

	/**
	 * Purpose: this API(application programming interface) is created for verifying
	 * new user who are register in the system verification is done by the email
	 * while registering new user verification mail has send to user email id and in
	 * this API(application programming interface) we are handling the verification
	 * process
	 * 
	 * @param token this token is JWT(JSON web token) token which is holding the
	 *              email in it while verifying user account you need to have there
	 *              email id and once we parse email id from the token then in
	 *              database making changes and putting that user active status as
	 *              true
	 * @return returns ResponseEntity which is holding the User object and
	 *         HttpStatus in that entity
	 */
	@PutMapping("/verify")
	public ResponseEntity<Response> verify(@RequestHeader String token) {
		LOG.info(Constant.CONTROLLER_VERIFY_REGISTER_METHOD);
		return new ResponseEntity<>(service.verify(token), HttpStatus.OK);
	}

	/**
	 * Purpose: this API(application programming interface) is created to login the
	 * user into application where in this we are accepting the email and password
	 * and if email and password verify into database successfully then we are going
	 * to print success message else print failure message
	 * 
	 * @param loginDTO response coming from front end will going to handle in this
	 *                 controller class as this request is login request so we are
	 *                 mapping this request into LoginDTO POJO class by
	 *                 using @RequestBody and then giving DTO to service to process
	 * @return ResponseEntity which is holding the String and HttpStatus in that
	 *         entity
	 */
	@PutMapping("/login")
	public ResponseEntity<Response> login(@Valid @RequestBody LoginDTO loginDTO) {
		LOG.info(Constant.CONTROLLER_LOGIN_METHOD);
		return new ResponseEntity<>(service.login(loginDTO), HttpStatus.OK);

	}

	/**
	 * Purpose: this API(application programming interface) is created for forgot
	 * password functionality in which user forgets there password and user facing
	 * problem while login into the system at that time user can use this
	 * API(application programming interface) to setup new password where in this
	 * API(application programming interface) user needs to provide email and system
	 * is going to generate email with JWT(JSON web token) token to setup new
	 * password
	 * 
	 * @param email input from the user to put the request for changing the password
	 * @return ResponseEntity which is holding the String and HttpStatus in that
	 *         entity
	 */
	@PutMapping("/forgetpassword")
	public ResponseEntity<Response> forgotPassword(@RequestHeader(name = "email") String email) {
		LOG.info(Constant.CONTROLLER_FORGOT_PASSWORD_METHOD);
		return new ResponseEntity<>(service.forgotPassword(email), HttpStatus.OK);
	}

	/**
	 * Purpose: this API(application programming interface) is created for setting
	 * new password into database the password is taken from the user end user has
	 * the link to set new password and in that link we are embed the email (email
	 * is encoded into JWT(JSON web token)) to which we want to setup new password
	 * 
	 * @param password parameter receives from the user end which is password which
	 *                 attach into the @RequestHeader
	 * @param token    this token is JWT(JSON web token) token which is holding the
	 *                 email in it while verifying user account you need to have
	 *                 there email id and once we parse email id from the token then
	 *                 in database making changes and putting that user active
	 *                 status as true
	 * @return ResponseEntity which is holding the String and HttpStatus in that
	 *         entity
	 */
	@PutMapping("/setpassword")
	public ResponseEntity<Response> setPassword(@RequestHeader(name = "token") String token,
			@RequestBody SetPasswordDTO setPasswordDTO) {
		LOG.info(Constant.CONTROLLER_SET_PASSWORD_METHOD);
		return new ResponseEntity<>(service.setPassword(token, setPasswordDTO), HttpStatus.OK);
	}

	/**
	 * Purpose: this API(application programming interface) is created for upload
	 * image for the profile picture of the user and this will store image into the
	 * path and maintain the location into database
	 * 
	 * @param image this is MultipartFile coming from the user end
	 * 
	 * @param email this parameter helps to specify on which user needs to set the
	 *              profile picture
	 * 
	 * @return ResponseEntity which is holding the String and HttpStatus in that
	 *         entity
	 * @throws Exception
	 */
	@PutMapping("/upload")
	public ResponseEntity<Response> upload(@RequestParam(name = "file") MultipartFile image,
			@RequestHeader(name = "email") String email) throws Exception {
		return new ResponseEntity<Response>(service.upload(image, email), HttpStatus.OK);
	}

	/**
	 * Purpose: this API(application programming interface) is created for update
	 * upload image for the profile picture of the user and this will store image
	 * into the path and maintain the location into database
	 * 
	 * @param image this is MultipartFile coming from the user end
	 * 
	 * @param email this parameter helps to specify on which user needs to set the
	 *              profile picture
	 * 
	 * @return ResponseEntity which is holding the String and HttpStatus in that
	 *         entity
	 * @throws IOException handles the IOException
	 */
	@PutMapping("/updateupload")
	public ResponseEntity<Response> updateUpload(@RequestParam(name = "file") MultipartFile image,
			@RequestHeader(name = "email") String email) throws Exception {
		return new ResponseEntity<Response>(service.updateUpload(image, email), HttpStatus.OK);
	}

	/**
	 * Purpose: this API(application programming interface) is created for delete
	 * image for the profile picture of the user and this will store image into the
	 * path and maintain the location into database
	 * 
	 * @param email this parameter helps to specify on which user needs to set the
	 *              profile picture
	 * @return ResponseEntity which is holding the String and HttpStatus in that
	 *         entity
	 * @throws IOException handles the IOException
	 */
	@DeleteMapping
	public ResponseEntity<Response> deleteProfile(@RequestHeader(name = "email") String email) throws IOException {
		return new ResponseEntity<Response>(service.deleteProfile(email), HttpStatus.OK);
	}

	@GetMapping("/getall")
	public ResponseEntity<Response> getAllUsers() {
		return new ResponseEntity<Response>(service.getAllUsers(), HttpStatus.OK);
	}

}
