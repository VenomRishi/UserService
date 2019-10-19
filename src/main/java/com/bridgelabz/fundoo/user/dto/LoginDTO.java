/******************************************************************************
 *  Purpose: This class is simple DTO(Data Transfer Object) class which can map
 *  		 the values to this class on the basis of the user response which
 *  		 is catch by @RequestBody annotation and then mapping into this 
 *  		 class
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   19-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.dto;

public class LoginDTO {
	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
