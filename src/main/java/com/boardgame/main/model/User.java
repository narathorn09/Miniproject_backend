package com.boardgame.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "User", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userID;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	private String userType;
	
	public User() {
		super();
	}
	
	public User(Long userID, String username, String password, String userType) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}
	
	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
