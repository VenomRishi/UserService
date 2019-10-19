/******************************************************************************
 *  Purpose: This is Utility class which has the business login which can be
 *  		 used at some stance in the application
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   19-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.utility;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class UserUtility {

	/**
	 * Purpose: This method is created to generate one simple mail message
	 * SimpleMailMessage class has the functionality to setting all the
	 * configuration for sending mail is done by using this class only
	 * 
	 * @param email input from user
	 * @param token input from program
	 * @return returns the object of SimpleMailMessage with assigning the values in
	 *         it
	 */
	public SimpleMailMessage sendMail(String email, String token) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("Setup new Password of UserService Application");
		simpleMailMessage.setText("http://localhost:8080/user/setpassword/" + token);
		return simpleMailMessage;
	}

}
