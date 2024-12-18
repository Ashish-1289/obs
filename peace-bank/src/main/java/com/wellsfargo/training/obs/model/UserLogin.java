package com.wellsfargo.training.obs.model;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

//import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
public class UserLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long loginid;
	@Column(unique = true)
	private String username;
	private String password;
	private long tpin;

	@OneToOne
	@JoinColumn(name = "u_id")
	private User us;
	
	public void setPassword(String password) {
		System.out.println("First :"+password);
		Base64.Encoder encoder = Base64.getEncoder();  // encrypt password in database field
        String normalString = password;
        System.out.println(password +" "+"normal :"+normalString);
        String encodedString = encoder.encodeToString(
        normalString.getBytes(StandardCharsets.UTF_8) );
        this.password = encodedString;
	}

	public UserLogin() {
	}

	public UserLogin(long loginid, long tpin, User us, String password, String username) {
		this.loginid = loginid;
		this.tpin = tpin;
		this.us = us;
		this.password = password;
		this.username = username;
	}

	public long getLoginid() {
		return loginid;
	}

	public void setLoginid(long loginid) {
		this.loginid = loginid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public long getTpin() {
		return tpin;
	}

	public void setTpin(long tpin) {
		this.tpin = tpin;
	}

	public User getUs() {
		return us;
	}

	public void setUs(User us) {
		this.us = us;
	}
}
