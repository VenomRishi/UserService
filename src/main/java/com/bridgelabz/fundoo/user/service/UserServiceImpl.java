package com.bridgelabz.fundoo.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundoo.user.configuration.UserConfiguration;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.user.utility.UserUtility;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConfiguration config;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private UserUtility userUtility;

	@Override
	public boolean login(LoginDTO loginDTO) {

		return userRepository.findAll().stream().anyMatch(i -> i.getEmail().equals(loginDTO.getEmail())
				&& config.passwordEncoder().matches(loginDTO.getPassword(), i.getPassword()));
	}

	@Override
	public boolean validateEmail(String email) {
		return userRepository.findAll().stream().anyMatch(i -> i.getEmail().equals(email));
	}

	@Override
	public boolean register(RegisterDTO registerDTO) {
		if (!validateEmail(registerDTO.getEmail())) {
			registerDTO.setPassword(config.passwordEncoder().encode(registerDTO.getPassword()));
			User user = config.modelMapper().map(registerDTO, User.class);
			userRepository.save(user);
			return true;
		}

		return false;
	}

	@Override
	public void forgotPassword(String email) {
		SimpleMailMessage sampleMailMessage = userUtility.sendMail(email);
		javaMailSender.send(sampleMailMessage);
	}

	@Override
	public void changePassword(String password) {

	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
