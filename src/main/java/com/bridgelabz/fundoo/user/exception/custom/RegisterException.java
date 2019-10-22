/******************************************************************************
 *  Purpose: This class is created for catching user exception
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   21-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.exception.custom;

public class RegisterException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Purpose: To create a custom exception handler of Register API of
	 * UserService
	 * 
	 * @param message exception which can generated from the program
	 */
	public RegisterException(String message) {
		super(message);
	}
}
