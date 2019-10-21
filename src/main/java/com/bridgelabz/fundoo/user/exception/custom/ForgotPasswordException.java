/******************************************************************************
 *  Purpose: This class is created for catching user exception
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   21-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.exception.custom;

public class ForgotPasswordException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ForgotPasswordException(String message) {
		super(message);
	}

}
