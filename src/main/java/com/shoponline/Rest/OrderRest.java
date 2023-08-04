package com.shoponline.Rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoponline.Entity.Order;
import com.shoponline.Service.AccountService;
import com.shoponline.Service.OrderService;

@RestController
public class OrderRest {
	@Autowired
	OrderService orderSer;
	
	@Autowired
	AccountService accSer;
	
	@GetMapping("api/order")
	public ResponseEntity<List<Order>> getAll(){
		return ResponseEntity.ok(orderSer.getAll());
	}
	
	@GetMapping("api/order/{id}")
	public ResponseEntity<Order> getOne(@PathVariable("id") Integer id){
		if(!orderSer.existsById(id)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(orderSer.getOne(id));
	}
	
	@PostMapping("api/order")
	public ResponseEntity<Order> create(Authentication auth,@RequestBody Order order){
		if(auth.isAuthenticated()) {
			order.setAccount(accSer.getOne(auth.getName()));
		}
		return ResponseEntity.ok(orderSer.save(order));
	}
	
	@PutMapping("api/order")
	public ResponseEntity<Order> update(Authentication auth,@RequestBody Order order){
		if(auth.isAuthenticated()) {
			order.setAccount(accSer.getOne(auth.getName()));
		}
		return ResponseEntity.ok(orderSer.save(order));
	}
	
	@DeleteMapping("api/order/{id}")
	public void delete(@PathVariable("id") Integer id){
		if(orderSer.existsById(id)) {
			orderSer.delete(id);
		}
	}
}
