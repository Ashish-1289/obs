package com.wellsfargo.training.obs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Payee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long fromAccount;

	public Payee() {
	}

	public Payee(long id, long fromAccount, long benAccount, String benName, String nickName) {
		this.id = id;
		this.fromAccount = fromAccount;
		this.benAccount = benAccount;
		this.benName = benName;
		this.nickName = nickName;
	}

	private long benAccount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public long getBenAccount() {
		return benAccount;
	}

	public void setBenAccount(long benAccount) {
		this.benAccount = benAccount;
	}

	public String getBenName() {
		return benName;
	}

	public void setBenName(String benName) {
		this.benName = benName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	private String benName;
	private String nickName;
}
