/******************************************************************************
 *  Purpose: This is interface of User Service class in the @Service it handles
 *  		 all the request coming from controller and which is then process
 *  		 in service class as it is interface so there is only declaration 
 *  		 of methods no defination is there
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   18-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.service;

import java.util.List;

import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.model.User;

public interface UserService {
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
	boolean login(LoginDTO loginDTO);

	/**
	 * Purpose: method for checking the email is there in database or not
	 * 
	 * @param input from program
	 * 
	 * @return true if email is found in database or else return false
	 */
	boolean validateEmail(String email);

	/**
	 * Purpose: method for registration of new user
	 * 
	 * @param registerDTO this is object of RegisterDTO class which is passed from
	 *                    controller this object is holding all the information
	 *                    which is coming from the user end
	 * @return returns true if user register successfully else application will
	 *         throw the exception
	 */
	boolean register(RegisterDTO registerDTO);

	/**
	 * Purpose: method is created for the sending the set password link on email if
	 * user forgets there password
	 * 
	 * @param email email id receives from the use from user response
	 */
	void forgotPassword(String email);

	/**
	 * Purpose: method is created for changing the password of current user
	 * 
	 * @param password input from user
	 * @param token    input from user url
	 */
	void setPassword(String password, String token);

	List<User> getAllUsers();

}
