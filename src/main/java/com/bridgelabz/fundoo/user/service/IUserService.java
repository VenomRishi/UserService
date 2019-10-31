/******************************************************************************
 *  Purpose: This is interface of User Service class in the @Service it handles
 *  		 all the request coming from controller and which is then process
 *  		 in service class as it is interface so there is only declaration 
 *  		 of methods no definition is there
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   18-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.service;

import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.dto.SetPasswordDTO;
import com.bridgelabz.fundoo.user.response.Response;

public interface IUserService {
	/**
	 * Purpose: method for login the user into the system
	 * 
	 * @param loginDTO this is object of RegisterDTO class which is passed from
	 *                 controller this object is holding all the information which
	 *                 is coming from the user end
	 * 
	 * @return returns ResponseEntity<Response> which contains the response of the
	 *         method
	 */
	Response login(LoginDTO loginDTO);

	/**
	 * Purpose: method for checking the email is there in database or not
	 * 
	 * @param input from program
	 * 
	 * @return true if email is found in database or else return false
	 */
//	boolean validateEmail(String email);

	/**
	 * Purpose: method for registration of new user
	 * 
	 * @param registerDTO this is object of RegisterDTO class which is passed from
	 *                    controller this object is holding all the information
	 *                    which is coming from the user end
	 * @return returns Response which contains the response of the method
	 */
	Response register(RegisterDTO registerDTO);

	/**
	 * Purpose: method is created for sending the email for activating the user
	 * account user only login to account only if user account is activated if the
	 * user wants to activate the account then he/she has to verify the email
	 * verification link
	 * 
	 * @param email from where the user wants to send email this parameter comes
	 *              from the user registration form
	 * 
	 */
	void registerVerificationSendEmail(String email);

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
	Response verify(String token);

	/**
	 * Purpose: method is created for the sending the set password link on email if
	 * user forgets there password
	 * 
	 * @param email email id receives from the use from user response
	 * 
	 * @return returns Response which contains the response of the method
	 */
	Response forgotPassword(String email);

	/**
	 * Purpose: method is created for changing the password of current user
	 * 
	 * @param setPasswordDTO input from user
	 * 
	 * @return returns Response which contains the response of the method
	 */
	Response setPassword(SetPasswordDTO setPasswordDTO);

}
