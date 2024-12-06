package com.wellsfargo.training.obs.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transactions_table")
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
public class Transact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private long id;

	public Transact() {
	}

	private long fromAcc;

	public Transact(long id, long fromAcc, String benName, long toAcc, long amount, Date tranDate, String nickName, String remarks, String transactType, long transactionNumber) {
		this.id = id;
		this.fromAcc = fromAcc;
		this.benName = benName;
		this.toAcc = toAcc;
		this.amount = amount;
		this.tranDate = tranDate;
		this.nickName = nickName;
		this.remarks = remarks;
		this.transactType = transactType;
		this.transactionNumber = transactionNumber;
	}

	private String benName;
	
	private long toAcc;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFromAcc() {
		return fromAcc;
	}

	public void setFromAcc(long fromAcc) {
		this.fromAcc = fromAcc;
	}

	public String getBenName() {
		return benName;
	}

	public void setBenName(String benName) {
		this.benName = benName;
	}

	public long getToAcc() {
		return toAcc;
	}

	public void setToAcc(long toAcc) {
		this.toAcc = toAcc;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTransactType() {
		return transactType;
	}

	public void setTransactType(String transactType) {
		this.transactType = transactType;
	}

	public long getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(long transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	private long amount;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date tranDate;
	
	private String nickName;
	
	private String remarks;
	
	private String transactType;
	@NonNull
	@Column(unique = true)
	private long transactionNumber;
}