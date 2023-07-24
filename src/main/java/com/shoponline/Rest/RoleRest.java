package com.shoponline.Rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoponline.Entity.Role;
import com.shoponline.Repository.RoleDAO;

@RestController
public class RoleRest {
	@Autowired
	RoleDAO roleDao;
	
	@GetMapping("/api/role")
	public ResponseEntity<List<Role>> listRole(){
		return ResponseEntity.ok(roleDao.findAll());
	}
	
	@GetMapping("/api/role/{id}")
	public ResponseEntity<Role> getRole(@PathVariable("id") String id){
		if(!roleDao.existsById(id)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(roleDao.findById(id).get());
	}
	
	@PostMapping("/api/role")
	public ResponseEntity<Role> post(@RequestBody Role role){
		roleDao.save(role);
		return ResponseEntity.ok(role);
	}
	
	@PutMapping("/api/role")
	public ResponseEntity<Role> put(@RequestBody Role role){
		roleDao.save(role);
		return ResponseEntity.ok(role);
	}
	
	@DeleteMapping("/api/role/{id}")
	public void put(@PathVariable("id") String id){
		roleDao.deleteById(id);
	}
}
