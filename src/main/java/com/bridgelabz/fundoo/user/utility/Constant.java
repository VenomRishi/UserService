/******************************************************************************
 *  Purpose: This is Static Reference Service class which holds all the static
 *  		 references which can be used in application throughout
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   23-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.utility;

public class Constant {
	/**
	 * Purpose: this is static variable declaration which will be used to use this
	 * variable to create a message
	 */

	public final static String BASE_URL = "http://192.168.0.74:4200/";
	public final static String SET_URI = "setpassword";
	public final static String VERIFY_URI = "verify";
	public final static String UPLOAD_FOLDER = "/home/admin1/Documents/workspace-spring/UserService/uploads/";

	// method related message
	public final static String SERVICE_LOGIN_METHOD = "method: login service";
	public final static String SERVICE_REGISTER_METHOD = "method: register service";
	public final static String SERVICE_REGISTER_VERIFICATION_METHOD = "method: register verification sending email";
	public final static String SERVICE_VERIFY_USER_METHOD = "method: verify service";
	public final static String SERVICE_FORGOT_PASSWORD_METHOD = "method: forgot password service";
	public final static String SERVICE_SET_PASSWORD_METHOD = "method: set password service";
	public final static String SERVICE_UPLOAD_PROFILE = "method: upload profile pic";
	public final static String SERVICE_UPDATE_UPLOAD_PROFILE = "method: update upload profile pic";
	public final static String SERVICE_DELETE_UPLOAD_PROFILE = "method: delete upload profile pic";
	public final static String SERVICE_GET_UPLOAD_PROFILE = "method: get upload profile pic";

	public final static String CONTROLLER_REGISTER_METHOD = "register controller API";
	public final static String CONTROLLER_VERIFY_REGISTER_METHOD = "verify registration controller API";
	public final static String CONTROLLER_LOGIN_METHOD = "login controller API";
	public final static String CONTROLLER_FORGOT_PASSWORD_METHOD = "forgot password controller API";
	public final static String CONTROLLER_SET_PASSWORD_METHOD = "reset password controller API";
	public final static String CONTROLLER_UPLOAD_PROFILE = "upload profile controller API";

	// user related message
	public final static String SUCCESS_LOGIN = "login successful";
	public final static String SUCCESS_REGISTER = "registration successful please verify in email verification";
	public final static String SUCCESS_VERIFY = "User updated and verify successful";
	public final static String SUCCESS_SET_PASSWORD = "User updated and password set successful";
	public final static String FAILED_LOGIN = "Invalid username and password and/or account is not active";
	public final static String FAILED_REGISTER = "registration failed";
	public final static String FAILED_TO_VERIFY = "failed to verify..!";
	public final static String FAILED_TO_SET_PASSWORD = "unable to set new password due to there's not email associated with your token";
	public final static String REGISTER_EMAIL_FOUND = "not found record ith this email cannot able to create new entry with this email";
	public final static String EMAIL_NOT_FOUND = " not found is database";
	public final static String UPLOAD_SUCCESS = "upload pic successfull";
	public final static String DELETE_PROFILE_SUCCESS = "delete profile pic successfull";
	public final static String GET_IMAGES_RESPONSE = "get images response";
	public final static String IMAGE_FORMAT_EXCEPTION = "Please upload proper image";
	public final static String IMAGE_UPDATE_FAILED = "Please upload pic first to update pic";
	public final static String GET_USER = "User details";

	// token key
	public final static String KEY_LOGIN = "loginkey";
	public final static String KEY_REGISTER_VERIFY = "verifykey";
	public final static String KEY_SET_PASSWORD = "secretkey";

	// email related
	public final static String EMAIL_SEND = "email is send to recipient";
	public final static String EMAIL_SEND_FOR_FORGOT_PASSWORD = "Email is send for forget password click on link to send new password";
	public final static String EMAIL_SUBJECT_VERIFY = "Verify your email in UserService Application";
	public final static String EMAIL_SUBJECT_SETPASSWORD = "Setup new Password of UserService Application";

	// exception related
	public static final String VALIDATION_EXCEPTION = "Validation Exception";

	// validation related
	public final static String VALIDATE_EMAIL = "Please provide a email";
	public final static String VALIDATE_PROPER_EMAIL = "Please provide a proper email";
	public final static String VALIDATE_PASSWORD = "Please provide a password";
	public final static String VALIDATE_FNAME = "Please provide a first name";
	public final static String VALIDATE_LNAME = "Please provide a last name";

	public final static int HTTP_STATUS_OK = 200;
	public final static int HTTP_STATUS_BAD_REQUEST = 400;
	public final static int HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;

	// rabbitmq related
	public final static String QUEUE_NAME = "user_service";
	public final static String ROUTING_KEY = "user_service";
	public final static String MESSAGE_QUEUE_EXCHANGE = "message_queue_exchange";
	public final static String MESSAGE_RECEIVED = "Message Received: ";

}
