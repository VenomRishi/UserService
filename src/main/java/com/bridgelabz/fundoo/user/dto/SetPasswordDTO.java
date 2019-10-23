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

//import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Size;

import com.bridgelabz.fundoo.user.service.StaticRefs;

public class SetPasswordDTO {

	@Size(min = 6, max = 30)
	@NotEmpty(message = StaticRefs.VALIDATE_PASSWORD)
	private String password;

	private String token;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
