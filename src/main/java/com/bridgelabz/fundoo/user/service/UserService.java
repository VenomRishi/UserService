package com.bridgelabz.fundoo.user.service;

import com.bridgelabz.fundoo.user.model.User;

public interface UserService {
	
	boolean login(String email, String password);
	
	boolean validateEmail(String email);

	boolean register(User user);
	
	void forgotPassword(String email);
	
	void changePassword(String password);

}
