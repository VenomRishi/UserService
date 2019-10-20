/******************************************************************************
 *  Purpose: This class is mainly used to throw the user defined exception in
 *  		 the response of this application
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   19-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.exception;


public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

	public UserException(String exception) {
		super(exception);
	}

	

}
