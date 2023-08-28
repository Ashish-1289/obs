package com.wellsfargo.training.obs.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.training.obs.model.Address;
import com.wellsfargo.training.obs.model.User;
import com.wellsfargo.training.obs.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class UserController {
	@Autowired
	private UserService uservice;
	
	private ObjectMapper objectMapper;
	
	public UserController(UserService UserService) {
		super();
		this.uservice = UserService;
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public static long generateRandom(int length) {
	    Random random = new Random();
	    char[] digits = new char[length];
	    digits[0] = (char) (random.nextInt(9) + '1');
	    for (int i = 1; i < length; i++) {
	        digits[i] = (char) (random.nextInt(10) + '0');
	    }
	    return Long.parseLong(new String(digits));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Validated @RequestBody  User user){
		
		Address address = user.getAddress();
		user.setAddress(address);
		address.setUser(user);
		user.setAnumber(generateRandom(12));
		user.setAbalance(0);
		user.setStatus(false);
		
		User registeruser = uservice.registerUser(user);
		
		if(registeruser != null) {
			return ResponseEntity.ok(registeruser.getAnumber());
		}
		else {
			return ResponseEntity.badRequest().body("Registration Failed");
		}
	}
	
}
