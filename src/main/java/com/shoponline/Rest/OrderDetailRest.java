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

import com.shoponline.Entity.OrderDetail;
import com.shoponline.Service.OrderDetailService;

@RestController
public class OrderDetailRest {
	@Autowired
	OrderDetailService orderDetailSer;
	
	@GetMapping("api/order-detail")
	public ResponseEntity<List<OrderDetail>> getAll(){
		return ResponseEntity.ok(orderDetailSer.getAll());
	}
	
	@GetMapping("api/order-detail/{id}")
	public ResponseEntity<OrderDetail> getOne(@PathVariable("id") Integer id){
		if(!orderDetailSer.existsById(id)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(orderDetailSer.getOne(id));
	}
	
	@PostMapping("api/order-detail")
	public ResponseEntity<OrderDetail> create(@RequestBody OrderDetail detail){
		return ResponseEntity.ok(orderDetailSer.save(detail));
	}
	
	@PutMapping("api/order-detail")
	public ResponseEntity<OrderDetail> update(@RequestBody OrderDetail detail){
		return ResponseEntity.ok(orderDetailSer.save(detail));
	}
	
	@DeleteMapping("api/order-detail/{id}")
	public void delete(@PathVariable("id") Integer id){
		if(orderDetailSer.existsById(id)) {
			orderDetailSer.delete(id);
		}
	}
	
	@GetMapping("api/best-selling")
	public ResponseEntity<Object[]> bestselling(){
		return ResponseEntity.ok(orderDetailSer.getBestSelling());
	}
	
	@GetMapping("api/orders-detail/{id}")
	public ResponseEntity<List<OrderDetail>> getOrderDetailByid(@PathVariable("id") Integer id){
		return ResponseEntity.ok(orderDetailSer.getByOrderId(id));
	}
}
