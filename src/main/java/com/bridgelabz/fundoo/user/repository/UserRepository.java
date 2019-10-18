/******************************************************************************
 *  Purpose: this interface is repository interface which can give service
 *  		 to use the implementation of JpaRepository this is the class
 *  		 which we are extending in this interface
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   18-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundoo.user.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
