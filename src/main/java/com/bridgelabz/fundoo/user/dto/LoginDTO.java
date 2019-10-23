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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.bridgelabz.fundoo.user.common.Constant;

public class LoginDTO {

	@NotEmpty(message = Constant.VALIDATE_EMAIL)
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = Constant.VALIDATE_PROPER_EMAIL)
	private String email;

	@Size(min = 6, max = 30)
	@NotEmpty(message = Constant.VALIDATE_PASSWORD)
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
