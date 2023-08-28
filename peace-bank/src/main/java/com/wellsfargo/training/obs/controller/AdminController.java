package com.wellsfargo.training.obs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.obs.dto.BalanceUpdate;
import com.wellsfargo.training.obs.dto.ShowUsers;
import com.wellsfargo.training.obs.exception.ResourceNotFoundException;
import com.wellsfargo.training.obs.model.Admin;
import com.wellsfargo.training.obs.model.User;
import com.wellsfargo.training.obs.model.UserLogin;
import com.wellsfargo.training.obs.service.AdminService;
import com.wellsfargo.training.obs.service.UserLoginService;
import com.wellsfargo.training.obs.service.UserService;

@RestController
@RequestMapping("api/admin")
public class AdminController {
	
	@Autowired
	private AdminService aservice;
	private UserService uservice;
	private UserLoginService ulservice;
	@PostMapping("/login")
	public Boolean adminLogin(@Validated @RequestBody Admin admin) throws ResourceNotFoundException
	{
		Boolean b = false;
		String username = admin.getAdminname();
		String password = admin.getApassword();
		
		Admin a = aservice.loginAdmin(username).orElseThrow(()->
		new ResourceNotFoundException("User not found for this id :: "));
		
		if(username.equals(a.getAdminname()) && password.equals(a.getApassword())) {
			b = true;
		}
		return b;
		
	}
	@GetMapping
	public ResponseEntity<?> showAllUsers(){
		List<User> l = new ArrayList<User>();
		try {
			l = uservice.getAllUsers();
		}
		catch(Exception e) {
			return new ResponseEntity<String>("Error :" + e.getMessage() , HttpStatus.BAD_REQUEST);
		}
		l = uservice.getAllUsers();
		List<ShowUsers> s = new ArrayList<ShowUsers>();
		ShowUsers u = new ShowUsers();
		for(int i=0;i<l.size();i++) {
			u.setName(l.get(i).getMname());
			u.setBalance(l.get(i).getAbalance());
			u.setNumber(l.get(i).getAnumber());
			u.setId(l.get(i).getUserlogin().getLoginid());
			s.add(u);
		}
		return ResponseEntity.ok(s);
	}
	@GetMapping("/status")
	public ResponseEntity<?>  showStatus(){
		List<User> l = new ArrayList<User>();
		try {
			l = uservice.getAllUsersStatus();
		}
		catch(Exception e) {
			return new ResponseEntity<String>("Error :" + e.getMessage() , HttpStatus.BAD_REQUEST);
		}
		l = uservice.getAllUsersStatus();
		List<ShowUsers> s = new ArrayList<ShowUsers>();
		ShowUsers u = new ShowUsers();
		for(int i=0;i<l.size();i++) {
			u.setName(l.get(i).getMname());
			u.setBalance(l.get(i).getAbalance());
			u.setNumber(l.get(i).getAnumber());
			u.setId(l.get(i).getUserlogin().getLoginid());
			s.add(u);
		}
		return ResponseEntity.ok(s);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> approveUsers(@PathVariable(value = "id") long Aid , User u){
		UserLogin ul = ulservice.findUser(Aid);
		long delid = ul.getUs().getId();
		User p = ul.getUs();
		if(u.isStatus()) {
			p.setStatus(true);
			uservice.registerUser(p);
			return ResponseEntity.ok("Approved by Admin");
		}
		else {
			uservice.deleteUser(delid);
			return ResponseEntity.badRequest().body("Not Approved by Admin");
		}
	}
	
	@PutMapping("/{id}/updatebalance")
	public ResponseEntity<?> updateBalance(@PathVariable(value = "id") long Aid , @RequestBody BalanceUpdate request){
		UserLogin ul = ulservice.findUser(Aid);
		if(ul == null) {
			return ResponseEntity.notFound().build();
		}
		User u= ul.getUs();
		long amount = request.getAmount();
		if("deposit".equals(request.getAction())) {
			u.setAbalance(u.getAbalance() + amount);
		}
		else if("withdraw".equals(request.getAction())) {
			if(u.getAbalance() >= amount) {
				u.setAbalance(u.getAbalance() - amount);
			}
			else {
				return ResponseEntity.badRequest().body("Insufficient Balance");
			}
		}
		else {
			return ResponseEntity.badRequest().body("Invalid action");
		}
		
		uservice.registerUser(u);
		return ResponseEntity.ok("Balance updated successfully");		
	}
}
