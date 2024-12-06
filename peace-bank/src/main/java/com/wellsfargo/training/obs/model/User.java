package com.wellsfargo.training.obs.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_table")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;

	public User() {
	}

	@Column(name = "first_name" )
	private String fname;

	public User(long id, String fname, String mname, String lname, String faname, String moname, String email, String aadhar, long phno, Date dob, String soi, long gs, long anumber, long abalance, boolean status, Address address, UserLogin userlogin) {
		this.id = id;
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.faname = faname;
		this.moname = moname;
		this.email = email;
		this.aadhar = aadhar;
		this.phno = phno;
		this.dob = dob;
		this.soi = soi;
		this.gs = gs;
		this.anumber = anumber;
		this.abalance = abalance;
		this.status = status;
		this.address = address;
		this.userlogin = userlogin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getFaname() {
		return faname;
	}

	public void setFaname(String faname) {
		this.faname = faname;
	}

	public String getMoname() {
		return moname;
	}

	public void setMoname(String moname) {
		this.moname = moname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public long getPhno() {
		return phno;
	}

	public void setPhno(long phno) {
		this.phno = phno;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSoi() {
		return soi;
	}

	public void setSoi(String soi) {
		this.soi = soi;
	}

	public long getGs() {
		return gs;
	}

	public void setGs(long gs) {
		this.gs = gs;
	}

	public long getAnumber() {
		return anumber;
	}

	public void setAnumber(long anumber) {
		this.anumber = anumber;
	}

	public long getAbalance() {
		return abalance;
	}

	public void setAbalance(long abalance) {
		this.abalance = abalance;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public UserLogin getUserlogin() {
		return userlogin;
	}

	public void setUserlogin(UserLogin userlogin) {
		this.userlogin = userlogin;
	}

	@Column(name = "middle_name")
	private String mname;
	@Column(name = "last_name")
	private String lname;
	@Column(name = "father_name")
	private String faname;
	@Column(name = "mother_name")
	private String moname;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String aadhar;
	@Column(unique = true)
	private long phno;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	@Column(name = "Source_of_income")
	private String soi;
	
	@Column(name = "Gross_Salary")
	private long gs;
	@Column(name = "account_number")
	@NonNull
	private long anumber;
	
	@Column(name = "account_balance")
	@NonNull
	private long abalance;
	
	private boolean status;

	@OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
	private Address address;
	@OneToOne(mappedBy = "us" , cascade = CascadeType.ALL)
	private UserLogin userlogin;
	
	
	
	
	
}
