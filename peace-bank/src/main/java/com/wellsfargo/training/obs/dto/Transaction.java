package com.wellsfargo.training.obs.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
public class Transaction {
private long id;
	
	private long fromAcc;
	
	private String benName;
	
	private long toAcc;
	
	private long amount;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date tranDate;
	
	private String nickName;
	
	private String remarks;
	
	private String type;

	public Transaction(long id, long pin, String type, String remarks, String nickName, Date tranDate, long amount, long toAcc, String benName, long fromAcc) {
		this.id = id;
		this.pin = pin;
		this.type = type;
		this.remarks = remarks;
		this.nickName = nickName;
		this.tranDate = tranDate;
		this.amount = amount;
		this.toAcc = toAcc;
		this.benName = benName;
		this.fromAcc = fromAcc;
	}

	private long pin;

	public Transaction() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPin() {
		return pin;
	}

	public void setPin(long pin) {
		this.pin = pin;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public long getToAcc() {
		return toAcc;
	}

	public void setToAcc(long toAcc) {
		this.toAcc = toAcc;
	}

	public String getBenName() {
		return benName;
	}

	public void setBenName(String benName) {
		this.benName = benName;
	}

	public long getFromAcc() {
		return fromAcc;
	}

	public void setFromAcc(long fromAcc) {
		this.fromAcc = fromAcc;
	}
}
