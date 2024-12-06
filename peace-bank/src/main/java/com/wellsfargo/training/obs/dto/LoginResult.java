package com.wellsfargo.training.obs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResult {
	private boolean Success;
	private long accountNumber;
	private long id;

	public void setSuccess(boolean success) {
		Success = success;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setId(long id) {
		this.id = id;
	}
}
