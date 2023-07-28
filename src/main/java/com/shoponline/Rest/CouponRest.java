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

import com.shoponline.Entity.Coupon;
import com.shoponline.Service.VoucherService;

@RestController
public class CouponRest {
	@Autowired
	VoucherService voucherSer;
	
	@GetMapping("api/coupon")
	public ResponseEntity<List<Coupon>> getList(){
		return ResponseEntity.ok(voucherSer.getAll());
	}
	
	@GetMapping("api/coupon/{id}")
	public ResponseEntity<Coupon> get(@PathVariable("id") Integer id){
		if(!voucherSer.existsById(id)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(voucherSer.getOne(id));
	}
	
	@PostMapping("api/coupon")
	public ResponseEntity<Coupon> create(@RequestBody Coupon coupon){
		return ResponseEntity.ok(voucherSer.save(coupon));
	}
	
	@PutMapping("api/coupon")
	public ResponseEntity<Coupon> update(@RequestBody Coupon coupon){
		return ResponseEntity.ok(voucherSer.save(coupon));
	}
	
	@DeleteMapping("api/coupon")
	public void delete(@PathVariable("id") Integer id){
			voucherSer.delete(id);
	}
}
