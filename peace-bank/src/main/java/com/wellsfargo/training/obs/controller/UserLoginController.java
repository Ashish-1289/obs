package com.wellsfargo.training.obs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.training.obs.dto.LoginResult;
import com.wellsfargo.training.obs.exception.ResourceNotFoundException;
import com.wellsfargo.training.obs.model.User;
import com.wellsfargo.training.obs.model.UserLogin;
import com.wellsfargo.training.obs.service.UserLoginService;
import com.wellsfargo.training.obs.service.UserService;


@RestController
@RequestMapping(value = "/api")
public class UserLoginController {
	
	@Autowired
	public UserLoginService ulservice;
	private UserService uservice;
	private ObjectMapper objectMapper;  //this is used to map fields with respective variables
	
	public UserLoginController(UserLoginService userlservice , UserService uservice) {
		super();
		this.ulservice = userlservice;
		this.uservice = uservice;
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	//when there are more fields than that are in model it throws a error so jsonNode is used to take the data and object mapper is used to map it.
	
	
	@PostMapping("/account")
	public ResponseEntity<String> Account( @RequestBody JsonNode jsonNode)throws JsonMappingException, JsonProcessingException{
		
		long account = jsonNode.get("anumber").asLong();
		try {   // it tries to fetch the user linked with account number if its null it throws a exception
			User u= uservice.fetchUser(jsonNode.get("anumber").asLong());
			UserLogin ul = objectMapper.treeToValue(jsonNode, UserLogin.class);
			ul.setUs(u);
			ulservice.registerUser(ul);
		}
		catch (Exception e) {  //it catches the error and prints it
			return new ResponseEntity<>("Error Message "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		User u = uservice.fetchUser(account);
		if(!u.isStatus()) {
			return ResponseEntity.badRequest().body("Not approved by the Admin, please contact Admin");
		}
			return new ResponseEntity<>("User Created Succesfully", HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	
	public LoginResult Login(@Validated @RequestBody UserLogin u) throws ResourceNotFoundException 
	{
		Boolean a = false;
		String UserName = u.getUsername();
		System.out.print(UserName);
		String Password = u.getPassword();
		
		UserLogin ul = ulservice.loginUser(UserName).orElseThrow(()->
		new ResourceNotFoundException("User not found for this id "));
		
		long accountnumber = ul.getUs().getAnumber();
		long id = ul.getUs().getId();
		
		if(UserName.equals(ul.getUsername()) && Password.equals(ul.getPassword())) {
			a = true;
		}
		// A custom made datatype object to return required fields to the front end.
		LoginResult lr = new LoginResult();
		lr.setSuccess(a);
		lr.setAccountNumber(accountnumber);
		lr.setId(id);
		return lr;
	}
 
}
