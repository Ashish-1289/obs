package com.wellsfargo.training.obs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.training.obs.model.User;
import com.wellsfargo.training.obs.model.UserLogin;
import com.wellsfargo.training.obs.service.EmailService;
import com.wellsfargo.training.obs.service.OtpService;
import com.wellsfargo.training.obs.service.UserLoginService;
import com.wellsfargo.training.obs.service.UserService;

@RestController
@RequestMapping("api/forgot-password")
public class ForgotPasswordController {
	@Autowired
	private UserService uservice;
	@Autowired
	private OtpService otpService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserLoginService ulservice;
	
	private ObjectMapper objectMapper;
	
	public ForgotPasswordController(UserService uservice , OtpService otpService , EmailService emailService, UserLoginService ulservice) {
		super();
		this.emailService = emailService;
		this.otpService = otpService;
		this.uservice = uservice;
		this.ulservice = ulservice;
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	@PostMapping
	public ResponseEntity<?> sendOtpToEmail(@RequestBody User u){
		User p;
		//System.out.println(email);
		try {
			p = uservice.fetchUserByEmail(u.getEmail());
		}
		catch(Exception e) {
//			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Error : " + e.getMessage() , HttpStatus.BAD_REQUEST);
		}
		p = uservice.fetchUserByEmail(u.getEmail());
		
		String otp = otpService.generateOtp();
		System.out.println(otp);
		otpService.storeOtp(u.getEmail(), otp);
		
		String subject = "Forgot Password OTP";
		String body = "Your OTP is" + otp;
		
		emailService.sendOtpEmail(u.getEmail(), subject, body);
		
		return ResponseEntity.ok("OTP sent to mail");
	}
	
	@PostMapping("/verify")
	public ResponseEntity<?> verifyOtp(@RequestBody JsonNode jsonNode)throws JsonMappingException{
		String storedOtp;
		String email;
		try {
			storedOtp = jsonNode.get("otp").asText();
			email = jsonNode.get("email").asText();
		}
		catch(Exception e) {
			return new ResponseEntity<>("Error Message "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		storedOtp = jsonNode.get("otp").asText();
		email = jsonNode.get("email").asText();
		String Otp = otpService.getOtp(email);
		if(storedOtp == null || !storedOtp.equals(Otp)) {
			return ResponseEntity.badRequest().body("Invalid Otp");
		}
		otpService.clearOtp(email);
		User p = uservice.fetchUserByEmail(email);
		long id = p.getUserlogin().getLoginid();
		return ResponseEntity.ok("id :" + id);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> resetPassword(@PathVariable(value = "id") long Aid , @RequestBody JsonNode jsonNode){
		String password = jsonNode.get("newPassword").asText();
		UserLogin ul = ulservice.findUser(Aid);
		ul.setPassword(password);
		return ResponseEntity.ok("Password updated Successfully");
	}
}
