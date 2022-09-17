package org.management.activation;

import lombok.Data;

@Data
public class ActivateAccount {

	private String registeredEmailId;

	private String newPassword;

	public String getRegisteredEmailId() {
		return registeredEmailId;
	}

	public void setRegisteredEmailId(String registeredEmailId) {
		this.registeredEmailId = registeredEmailId;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
	
