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

import com.shoponline.Entity.Order;
import com.shoponline.Service.OrderService;

@RestController
public class OrderRest {
	@Autowired
	OrderService orderSer;
	
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
	public ResponseEntity<Order> create(@RequestBody Order order){
		return ResponseEntity.ok(orderSer.save(order));
	}
	
	@PutMapping("api/order")
	public ResponseEntity<Order> update(@RequestBody Order order){
		return ResponseEntity.ok(orderSer.save(order));
	}
	
	@DeleteMapping("api/order/{id}")
	public void delete(@PathVariable("id") Integer id){
		if(orderSer.existsById(id)) {
			orderSer.delete(id);
		}
	}
}
