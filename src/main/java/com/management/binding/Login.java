package com.management.binding;

import lombok.Data;

@Data
public class Login {

	private String email;

	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String emaiId) {
		this.email = emaiId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
