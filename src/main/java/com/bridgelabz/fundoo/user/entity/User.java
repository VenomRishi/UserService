/******************************************************************************
 *  Purpose: Class is implemented for creating the POJO class
 *  		 @Entity will tell the spring framework that this is POJO class
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   18-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "u_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;

	@Column(name = "u_fname")
	@NotNull
	private String fname;

	@Column(name = "u_lname")
	@NotNull
	private String lname;

	@Column(name = "u_email")
	@NotNull
	private String email;

	@Column(name = "u_password")
	@NotNull
	private String password;

	@Column(name = "u_profile")
	private String profile;

	@Column(name = "u_reg_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date regDate;

	@Column(name = "u_update_date")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updateDate;

	@Column(name = "u_active", columnDefinition = "boolean default false")
	private boolean isActive;

}
