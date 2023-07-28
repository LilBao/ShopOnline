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

import com.shoponline.Entity.ProductDetail;
import com.shoponline.Service.ProductDetailService;

@RestController
public class ProductDetailRest {
	@Autowired
	ProductDetailService productDetailSer;
	
	@GetMapping("api/product-detail/{id}")
	public ResponseEntity<ProductDetail> getOne(@PathVariable("id") Integer id){
		return ResponseEntity.ok(productDetailSer.getOne(id));
	}
	
	@GetMapping("api/product-list-detail/{id}")
	public ResponseEntity<List<ProductDetail>> getProductId(@PathVariable("id") Integer id){
		return ResponseEntity.ok(productDetailSer.getByProduct(id));
	}
	
	@PostMapping("/api/product-detail")
	public ResponseEntity<ProductDetail> create(@RequestBody ProductDetail productDetail){
		return ResponseEntity.ok(productDetailSer.save(productDetail));
	}
	
	@PutMapping("/api/product-detail")
	public ResponseEntity<ProductDetail> update(@RequestBody ProductDetail productDetail){
		return ResponseEntity.ok(productDetailSer.save(productDetail));
	}
	
	@DeleteMapping("/api/product-detail/{id}")
	public void delete(@PathVariable("id") Integer id){
		productDetailSer.delete(id);
	}
}
