package com.shoponline.Rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoponline.Entity.Authority;
import com.shoponline.Repository.AccountDAO;
import com.shoponline.Repository.AuthorizeDAO;
import com.shoponline.Repository.RoleDAO;

@RestController
public class AuthorizeRest {
	
	@Autowired
	RoleDAO roleDao;
	
	@Autowired
	AuthorizeDAO authDao;
	
	@Autowired
	AccountDAO accDao;
	
	@GetMapping("api/authorities")
	public Map<String,Object> listAuthor(){
		Map<String, Object> data = new HashMap<>();
		data.put("authorities", authDao.findAll());
		data.put("roles", roleDao.findAll());
		data.put("accounts", accDao.findAll());
		return data;
	}
	
	@PostMapping("api/authorities")
	public ResponseEntity<Authority> post(@RequestBody Authority author){
		authDao.save(author);
		return ResponseEntity.ok(author);
	}
	
	@DeleteMapping("api/authorities/{id}")
	public void delete(@PathVariable("id") Integer id){
		authDao.deleteById(id);
	}
}
