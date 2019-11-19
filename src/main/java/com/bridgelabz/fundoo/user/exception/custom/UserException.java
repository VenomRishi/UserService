/******************************************************************************
 *  Purpose: This class is created for catching user exception
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   04-11-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.exception.custom;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserException(String message) {
		super(message);
	}

}
