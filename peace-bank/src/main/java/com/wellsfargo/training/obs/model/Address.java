package com.wellsfargo.training.obs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressid;
	private String rstreet;
	private String rcity;
	private String rpincode;
	private String pstreet;
	private String pcity;

	public Address() {
	}

	public Address(long addressid, String rstreet, String rcity, String rpincode, String pstreet, String pcity, String ppincode, User user) {
		this.addressid = addressid;
		this.rstreet = rstreet;
		this.rcity = rcity;
		this.rpincode = rpincode;
		this.pstreet = pstreet;
		this.pcity = pcity;
		this.ppincode = ppincode;
		this.user = user;
	}

	public long getAddressid() {
		return addressid;
	}

	public void setAddressid(long addressid) {
		this.addressid = addressid;
	}

	public String getRstreet() {
		return rstreet;
	}

	public void setRstreet(String rstreet) {
		this.rstreet = rstreet;
	}

	public String getRcity() {
		return rcity;
	}

	public void setRcity(String rcity) {
		this.rcity = rcity;
	}

	public String getRpincode() {
		return rpincode;
	}

	public void setRpincode(String rpincode) {
		this.rpincode = rpincode;
	}

	public String getPstreet() {
		return pstreet;
	}

	public void setPstreet(String pstreet) {
		this.pstreet = pstreet;
	}

	public String getPcity() {
		return pcity;
	}

	public void setPcity(String pcity) {
		this.pcity = pcity;
	}

	public String getPpincode() {
		return ppincode;
	}

	public void setPpincode(String ppincode) {
		this.ppincode = ppincode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String ppincode;
	
	@OneToOne
	@JoinColumn(name = "user_id" )
	private User user;
}
