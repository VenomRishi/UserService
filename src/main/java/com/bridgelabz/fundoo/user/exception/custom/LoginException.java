/******************************************************************************
 *  Purpose: This class is created for catching user exception
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   21-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.exception.custom;

public class LoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;

//	@Override
//	public synchronized Throwable fillInStackTrace() {
//		return this;
//	}

	public LoginException(String message) {
		super(message);
	}

}
