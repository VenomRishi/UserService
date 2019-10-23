/******************************************************************************
 *  Purpose: This is Static Reference Service class which holds all the static
 *  		 references which can be used in application throughout
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   23-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.service;

public class StaticRefs {
	/**
	 * Purpose: this is static variable declaration which will be used to use this
	 * variable to create a message
	 */

	public final static String BASE_URL = "http://localhost:8080/user/";
	public final static String SET_URI = "setpassword/";
	public final static String VERIFY_URI = "verify/";

	// method related message
	final static String SERVICE_LOGIN_METHOD = "method: login service";
	final static String SERVICE_REGISTER_METHOD = "method: register service";
	final static String SERVICE_REGISTER_VERIFICATION_METHOD = "method: register verification sending email";
	final static String SERVICE_VERIFY_USER_METHOD = "method: verify service";
	final static String SERVICE_FORGOT_PASSWORD_METHOD = "method: forgot password service";
	final static String SERVICE_SET_PASSWORD_METHOD = "method: set password service";

	public final static String CONTROLLER_REGISTER_METHOD = "register controller API";
	public final static String CONTROLLER_VERIFY_REGISTER_METHOD = "verify registration controller API";
	public final static String CONTROLLER_LOGIN_METHOD = "login controller API";
	public final static String CONTROLLER_FORGOT_PASSWORD_METHOD = "forgot password controller API";
	public static final String CONTROLLER_SET_PASSWORD_METHOD = "reset password controller API";

	// user related message
	final static String SUCCESS_LOGIN = "login successful";
	final static String SUCCESS_REGISTER = "registration successful please verify in email verification";
	final static String SUCCESS_VERIFY = "User updated and verify successful";
	final static String SUCCESS_SET_PASSWORD = "User updated and password set successful";
	final static String FAILED_LOGIN = "Invalid username and password and/or account is not active";
	final static String FAILED_REGISTER = "registration failed";
	final static String FAILED_TO_VERIFY = "failed to verify..!";
	final static String FAILED_TO_SET_PASSWORD = "unable to set new password due to there's not email associated with your token";
	final static String REGISTER_EMAIL_FOUND = "not found record ith this email cannot able to create new entry with this email";
	final static String EMAIL_NOT_FOUND = " not found is database";

	// token key
	final static String KEY_REGISTER_VERIFY = "verifykey";
	final static String KEY_SET_PASSWORD = "secretkey";

	// email related
	final static String EMAIL_SEND = "email is send to recipient";
	final static String EMAIL_SEND_FOR_FORGOT_PASSWORD = "Email is send for forget password click on link to send new password";
	public final static String EMAIL_SUBJECT_VERIFY = "Verify your email in UserService Application";
	public final static String EMAIL_SUBJECT_SETPASSWORD = "Setup new Password of UserService Application";

	// exception related
	public static final String VALIDATION_EXCEPTION = "Validation Exception";

	// validation related
	public final static String VALIDATE_EMAIL = "Please provide a email";
	public final static String VALIDATE_PROPER_EMAIL = "Please provide a proper email";
	public final static String VALIDATE_PASSWORD = "Please provide a password";
	public final static String VALIDATE_FNAME="Please provide a first name";
	public final static String VALIDATE_LNAME="Please provide a last name";
	
}
