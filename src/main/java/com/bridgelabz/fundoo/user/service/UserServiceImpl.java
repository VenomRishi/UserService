package com.bridgelabz.fundoo.user.service;

import java.util.Date;
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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
		// check email is there in database or not
		if (validateEmail(email)) {
			// code for sending email to recipient
			String token = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
			SimpleMailMessage sampleMailMessage = userUtility.sendMail(email, token);
			javaMailSender.send(sampleMailMessage);
		} else {
			
		}
	}

	@Override
	public void changePassword(String password, String token) {
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		System.out.println(claims.getSubject());
		User user = userRepository.findAll().stream().filter(i -> i.getEmail().equals(claims.getSubject())).findAny()
				.orElse(null);
		user.setPassword(config.passwordEncoder().encode(password));
		userRepository.save(user);

	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
