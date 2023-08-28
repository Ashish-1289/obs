package com.wellsfargo.training.obs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.training.obs.dto.BalanceUpdate;
import com.wellsfargo.training.obs.dto.ShowTransaction;
import com.wellsfargo.training.obs.dto.Transaction;
import com.wellsfargo.training.obs.model.Transact;
import com.wellsfargo.training.obs.model.User;
import com.wellsfargo.training.obs.model.UserLogin;
import com.wellsfargo.training.obs.service.TransactService;
import com.wellsfargo.training.obs.service.UserLoginService;
import com.wellsfargo.training.obs.service.UserService;

@RestController
@RequestMapping("/api/transaction")
public class TransactController {

	@Autowired
	private TransactService tservice;
	private UserLoginService ulservice;
	private UserService uservice;
	
	public TransactController(TransactService tservice , UserLoginService ulservice , UserService uservice) {
		this.tservice = tservice;
		this.ulservice = ulservice;
		this.uservice = uservice;
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
	
	@PutMapping
	public ResponseEntity <?> createTransact(@Validated @RequestBody Transaction transact){
		
		long amount = transact.getAmount();
		long toaccount = transact.getToAcc();
		User u = uservice.fetchUser(transact.getFromAcc());
		long fromaccount = transact.getFromAcc();
		User fromUser = uservice.fetchUser(fromaccount);
		User toUser = uservice.fetchUser(toaccount);
		long pin = fromUser.getUserlogin().getTpin();
		if(fromUser == null || toUser == null || pin != transact.getPin()) {
			return ResponseEntity.badRequest().body("Invalid Action Please check from and to user");
		}
		if(fromUser.getAbalance() < amount) {
			return ResponseEntity.badRequest().body("Insufficient Balance");
		}
		if("RTGS".equals(transact.getType())) {
			if(transact.getAmount() < 200000 || transact.getAmount() > 1000000) {
				return ResponseEntity.badRequest().body("Cannot initiate transaction under RTGS");
			}
		}
		if("NEFT".equals(transact.getType())) {
			if(transact.getAmount() > 1000000) {
				return ResponseEntity.badRequest().body("Cannot initiate transaction under NEFT");
			}
		}
		if("IMPS".equals(transact.getType())) {
			if(transact.getAmount() > 200000) {
				return ResponseEntity.badRequest().body("Cannot initiate transaction under IMPS");
			}
		}
		fromUser.setAbalance(fromUser.getAbalance() - amount);
		toUser.setAbalance(toUser.getAbalance() + amount);
		
		Transact t = new Transact();
		
		t.setAmount(amount);
		t.setBenName(transact.getBenName());
		t.setFromAcc(fromaccount);
		t.setTransactionNumber(generateRandom(10));
		t.setToAcc(toaccount);
		t.setNickName(transact.getNickName());
		t.setRemarks(transact.getRemarks());
		t.setTranDate(transact.getTranDate());
		t.setTransactType(transact.getType());
		tservice.registerTransact(t);
		
		return ResponseEntity.ok("Transaction Successful");
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
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTransactions(@PathVariable(value = "id") long id){
		UserLogin ul = new UserLogin();
		try {
			ul = ulservice.findUser(id);
		}
		catch(Exception e){
			return new ResponseEntity<String>("Error :" + e.getMessage() , HttpStatus.BAD_REQUEST);
		}
		List <ShowTransaction> l = new ArrayList<ShowTransaction>();
		List<Transact> t = new ArrayList<Transact>();
		long account = ul.getUs().getAnumber();
		try {
			t = tservice.showTransact(account);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("Error :" + e.getMessage() , HttpStatus.BAD_REQUEST);
		}
		t = tservice.showTransact(account);
		for(int i=0;i<t.size();i++) {
			if(t.get(i).getFromAcc() == account) {
				ShowTransaction s = new ShowTransaction();
				s.setT(t.get(i));
				s.setTransType("Debit");
				l.add(s);
			}
			else {
				ShowTransaction s = new ShowTransaction();
				s.setT(t.get(i));
				s.setTransType("Credit");
				l.add(s);
			}
		}
		return ResponseEntity.ok(l);
	}
}