/******************************************************************************
 *  Purpose: Class is implemented for handling the request from the user
 *  		 @RestController defines that it will deal with the rest controller
 *  		 @RequestMapping will handle the request
 *  		 in this class handling request related user
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   18-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/login")
	public boolean login(@RequestBody User user) {
		String email = user.getEmail();
		String password = user.getPassword();
		return service.login(email, password);

	}

	@PostMapping("/register")
	public boolean register(@RequestBody User user) {
		return service.register(user);
	}

	@GetMapping("/fp/{email}")
	public boolean forgotPassword(@PathVariable(name = "email") String email) {
		service.forgotPassword(email);
		return true;
	}

}
