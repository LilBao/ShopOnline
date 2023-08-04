package com.shoponline.Rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoponline.Entity.Account;
import com.shoponline.Entity.Authority;
import com.shoponline.Repository.AccountDAO;
import com.shoponline.Repository.AuthorizeDAO;
import com.shoponline.Repository.RoleDAO;

@RestController
public class AccountRestAPI {
	@Autowired
	AccountDAO dao;
	
	@Autowired
	PasswordEncoder pe;
	
	@Autowired
	AuthorizeDAO authDao;
	
	@Autowired
	RoleDAO roleDao;
			
	@GetMapping("/api/auth/{username}")
	public ResponseEntity<Account> get(@PathVariable("username") String username){
		if(!dao.existsById(username)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dao.findById(username).get());
	}
	
	@GetMapping("/api/auth")
	public ResponseEntity<List<Account>> getAll(){
		return ResponseEntity.ok(dao.findAll());
	}
	
	@PostMapping("/api/auth")
	public ResponseEntity<Account> post(@RequestBody Account account){
		Authority auth = new Authority();
		account.setAvatar("");
		account.setPassword(pe.encode(account.getPassword()));
		auth.setAccount(account);
		auth.setRole(roleDao.findById("CUS").get());
		dao.save(account);
		authDao.save(auth);
		return ResponseEntity.ok(account);
	}
	
	@PutMapping("/api/auth")
	public ResponseEntity<Account> put(@RequestBody Account account){
		dao.save(account);
		return ResponseEntity.ok(account);
	}
	
	@DeleteMapping("api/auth/{username}")
	public ResponseEntity<Account> delete(@PathVariable("username") String username) {
		if(!dao.existsById(username)) {
			return ResponseEntity.notFound().build();
		}
		Account account = dao.findById(username).get();
		dao.deleteById(username);
		return ResponseEntity.ok(account);
	}
}
