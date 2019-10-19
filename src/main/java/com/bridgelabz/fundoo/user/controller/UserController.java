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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/get")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}

	@PostMapping("/login")
	public ResponseEntity<Boolean> login(@RequestBody LoginDTO loginDTO) {
		return new ResponseEntity<>(service.login(loginDTO), HttpStatus.OK);

	}

	@PostMapping("/register")
	public ResponseEntity<Boolean> register(@RequestBody RegisterDTO registerDTO) {
		return new ResponseEntity<>(service.register(registerDTO), HttpStatus.OK);
	}

	@PostMapping("/fp/{email}")
	public boolean forgotPassword(@PathVariable(name = "email") String email) {
		service.forgotPassword(email);
		return true;
	}

}
