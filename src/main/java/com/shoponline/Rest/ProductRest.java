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

import com.shoponline.Entity.Product;
import com.shoponline.Service.FileService;
import com.shoponline.Service.ProductService;

@RestController
public class ProductRest {
	@Autowired
	ProductService prdSer;
	
	@Autowired
	FileService fileSer;
	
	@GetMapping("/api/product")
	public ResponseEntity<List<Product>> getList(){
		return ResponseEntity.ok(prdSer.getAll());
	}
	
	@GetMapping("/api/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id){
		if(!prdSer.existsById(id)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(prdSer.getOne(id));
	}
	
	@PostMapping("/api/product")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		return ResponseEntity.ok(prdSer.save(product));
	}
	
	@PutMapping("api/product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		return ResponseEntity.ok(prdSer.save(product));
	}
	
	@DeleteMapping("/api/product/{id}")
	public void deleteProduct(@PathVariable("id") Integer id) {
		prdSer.delete(id);
	}
}
