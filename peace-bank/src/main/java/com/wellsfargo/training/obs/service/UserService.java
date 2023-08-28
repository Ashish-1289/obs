package com.wellsfargo.training.obs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.training.obs.model.User;
import com.wellsfargo.training.obs.repository.UserRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userrepo;
	
	public User registerUser(User u) {
		return userrepo.save(u);
	}
//	public Optional<User> loginUser(String username){
//		return userrepo.findByUsername(username);
//	}
	public User fetchUser(long a){
		Optional<User> u = userrepo.findByAnumber(a);
		return u.get();
	}
	public User fetchUserByEmail(String email) {
		Optional<User> u = userrepo.findByEmail(email);
		return u.get();
	}
	public List<User> getAllUsers(){
		return userrepo.findAll();
	}
	public List<User> getAllUsersStatus(){
		return userrepo.findByStatusFalse();
	}
	public void deleteUser(Long id) {
		userrepo.deleteById(id);
	}
}
