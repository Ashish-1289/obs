package com.wellsfargo.training.obs.dto;

import lombok.*;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class BalanceUpdate {
	private String action;
	private long amount;

	public String getAction() {
		return action;
	}

	public BalanceUpdate() {
	}

	public BalanceUpdate(String action, long amount) {
		this.action = action;
		this.amount = amount;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
}
