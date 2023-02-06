package com.product.Product.dto;

public class UserAuth {
	
	private String username;
	private String password;
	
	public UserAuth() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAuth(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

	@Override
	public String toString() {
		return "UserAuth [username=" + username + ", password=" + password + "]";
	}
	
	
	

}
