package com.wellsfargo.training.obs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
public class ShowUsers {
	
	private long id;
	private String name;
	private long number;
	private long balance;

	public ShowUsers() {
	}

	public long getId() {
		return id;
	}

	public ShowUsers(long id, String name, long number, long balance) {
		this.id = id;
		this.name = name;
		this.number = number;
		this.balance = balance;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}
}
