package com.va.week6.model;

/*
 * @author - Nitin John
 * @date -
 * description - this is model class for student registration details, with all getters and setters
 * 
 */

public class Student {

	private String username;
	private String password;
	private String mobileNumber;
	private String email;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
