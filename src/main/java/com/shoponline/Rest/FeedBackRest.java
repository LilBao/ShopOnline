package com.shoponline.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoponline.Repository.FeedBackDAO;

@RestController
public class FeedBackRest {
	@Autowired
	FeedBackDAO dao;
	@GetMapping("api/top-product-feedback")
	public ResponseEntity<Object[]> get(){
		return ResponseEntity.ok(dao.getRatingFb());
	}
}
