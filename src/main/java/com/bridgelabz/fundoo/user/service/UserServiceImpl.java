package com.bridgelabz.fundoo.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.user.configuration.UserConfiguration;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConfiguration config;

	@Override
	public boolean login(String email, String password) {

		return userRepository.findAll().stream().anyMatch(
				i -> i.getEmail().equals(email) && config.passwordEncoder().matches(password, i.getPassword()));
	}

	@Override
	public boolean validateEmail(String email) {
		return userRepository.findAll().stream().anyMatch(i -> i.getEmail().equals(email));
	}

	@Override
	public boolean register(User user) {
		if (!validateEmail(user.getEmail())) {
			user.setPassword(config.passwordEncoder().encode(user.getPassword()));
			userRepository.save(user);
			return true;
		}

		return false;
	}

	@Override
	public void forgotPassword(String email) {

	}

	@Override
	public void changePassword(String password) {

	}

	@Override
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

}
