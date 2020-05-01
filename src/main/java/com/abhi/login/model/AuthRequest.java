package com.abhi.login.model;

public class AuthRequest {

	private String userName;
	private String userPass;

	public AuthRequest(String userName, String userPass) {
		super();
		this.userName = userName;
		this.userPass = userPass;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

}
