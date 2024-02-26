package com.adp.leavemanagment.modal;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LoginUser implements Serializable {
		
	@Id
	@Column(updatable = false)
	private String userId;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private String password;
	
	private Date lastLoginDate;
	
	@Column(nullable = false, updatable = false)
	private Date createdDate;
	
	@Column(nullable = false)
	private Date modifiedDate;
	
	@Column(nullable = false)
	private String role;
	
	@Column(nullable = false)
	private boolean isActive;
	
	
	public LoginUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LoginUser(String userId, String firstName, String lastName, String password) {
		super();
		Date todayDAate = new Date();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.lastLoginDate = todayDAate;
		this.createdDate = todayDAate;
		this.modifiedDate = todayDAate;
		this.role = "USER";
		this.isActive = true;
		
	}
	

	public LoginUser(String userId, String firstName, String lastName, String password, Date lastLoginDate,
			Date createdDate, Date modifiedDate, String role, boolean isActive) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.lastLoginDate = lastLoginDate;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.role = role;
		this.isActive = isActive;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	

}
