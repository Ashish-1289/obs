package com.wellsfargo.training.obs.dto;

import com.wellsfargo.training.obs.model.Transact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
public class ShowTransaction {
	
	private Transact t;

	public Transact getT() {
		return t;
	}

	public ShowTransaction() {
	}

//	public ShowTransaction(Transact t, String transType) {
//		this.t = t;
//		this.transType = transType;
//	}

	public void setT(Transact t) {
		this.t = t;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	private String transType;
}
