package com.shoponline.Rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shoponline.Entity.Product;
import com.shoponline.Repository.ProductDAO;
import com.shoponline.Service.FileService;

@RestController
public class ProductRest {
	@Autowired
	ProductDAO productDao;
	
	@Autowired
	FileService fileSer;
	
	@GetMapping("/api/product")
	public ResponseEntity<List<Product>> getList(){
		return ResponseEntity.ok(productDao.findAll());
	}
	
	@GetMapping("/api/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id){
		if(!productDao.existsById(id)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(productDao.findById(id).get());
	}
	
	@PostMapping("/api/product")
	public ResponseEntity<Product> getProduct(@RequestBody Product product,@RequestPart("image") String file,
			@RequestPart("images") MultipartFile[] multipleFile){
		String[] images = fileSer.upload("image", multipleFile).toArray(String[] :: new);
		product.setThumbnail(file);
		product.setListImage(images);
		productDao.save(product);
		return ResponseEntity.ok(product);
	}
}
