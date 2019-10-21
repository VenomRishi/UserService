/******************************************************************************
 *  Purpose: Class is implemented for creating the POJO class
 *  		 @Entity will tell the spring framework that this is POJO class
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   18-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.model;

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


@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "u_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
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

	@Column(name = "u_reg_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@NotNull
	private Date regDate;

	@Column(name = "u_update_date")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@NotNull
	private Date updateDate;

	@Column(name = "u_active", columnDefinition = "boolean default false")
	private boolean isActive;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", password="
				+ password + ", regDate=" + regDate + ", updateDate=" + updateDate + "]";
	}

}
