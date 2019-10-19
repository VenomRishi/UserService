package com.bridgelabz.fundoo.user.utility;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class UserUtility {

	public SimpleMailMessage sendMail(String email) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("Setup new Password of UserService Application");
		simpleMailMessage.setText("Checking mail now");
		return simpleMailMessage;
	}

}
