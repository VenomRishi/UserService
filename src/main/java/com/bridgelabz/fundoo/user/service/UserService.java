package com.bridgelabz.fundoo.user.service;

import java.util.List;

import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.model.User;

public interface UserService {

	boolean login(LoginDTO loginDTO);

	boolean validateEmail(String email);

	boolean register(RegisterDTO registerDTO);

	void forgotPassword(String email);

	void changePassword(String password, String token);

	List<User> getAllUsers();

}
