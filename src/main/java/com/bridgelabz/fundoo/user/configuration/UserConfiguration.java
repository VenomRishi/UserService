/******************************************************************************
 *  Purpose: This is configuration class which holds all the configuration
 *  		 related application
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   19-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.context.annotation.Configuration;;

@Configuration
public class UserConfiguration {
	/**
	 * Purpose: this method is created to achieve the BCryptPasswordEncoder feature
	 * in application BCrypt is one of the best hashing algorithm to encrypt
	 * password into cipher text so even if any hacker attacks to our database the
	 * credentials we are storing into database in not readable
	 * 
	 * @Bean annotation is used to getting the object bean at runtime
	 * 
	 * @return returns the object of BCryptPasswordEncoder class
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Purpose: this method is created for the functionality providing to
	 * application of ModelMapper this is used to map the simple POJO to @Entity
	 * class
	 * 
	 * @Bean annotation is used to getting the object bean at runtime
	 * 
	 * @return returns the object of ModelMapper class
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
