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

import com.shoponline.Entity.Category;
import com.shoponline.Repository.CategoryDAO;

@RestController
public class CateRest {
	
	@Autowired
	CategoryDAO cateDao;
	
	@GetMapping("/api/cate")
	public ResponseEntity<List<Category>> listCate(){
		return ResponseEntity.ok(cateDao.findAll());
	}
	
	@GetMapping("/api/cate/{id}")
	public ResponseEntity<Category> get(@PathVariable("id") Integer id){
		if(!cateDao.existsById(id)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(cateDao.findById(id).get());
	}
	
	@PostMapping("/api/cate")
	public ResponseEntity<Category> create(@RequestBody Category cate){
		cateDao.save(cate);
		return ResponseEntity.ok(cate);
	}
	
	@PutMapping("/api/cate")
	public ResponseEntity<Category> update(@RequestBody Category cate){
		cateDao.save(cate);
		return ResponseEntity.ok(cate);
	}
	
	@DeleteMapping("/api/cate/{id}")
	public void delete(@PathVariable("id") Integer id) {
		cateDao.deleteById(id);
	}
}
