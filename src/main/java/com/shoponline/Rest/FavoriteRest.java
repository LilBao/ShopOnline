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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoponline.Entity.Favorite;
import com.shoponline.Service.AccountService;
import com.shoponline.Service.FavoriteService;
import com.shoponline.Service.ProductService;

@RestController
public class FavoriteRest {
	@Autowired
	FavoriteService favoriteSer;
	
	@Autowired
	AccountService accSer;
	
	@Autowired
	ProductService prdSer;
	
	@GetMapping("/api/wishlist")
	public ResponseEntity<List<Favorite>> getWishList(@RequestParam("username") String username) {
		return ResponseEntity.ok(favoriteSer.getByUser(username));
	}
	
	@GetMapping("/api/wishlist/{id}")
	public ResponseEntity<Favorite> getOne(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(favoriteSer.getOne(id));
	}
	
	@PostMapping("/api/wishlist")
	public ResponseEntity<Favorite> create(@RequestBody Favorite favorite){
		return ResponseEntity.ok(favoriteSer.save(favorite));
	}
	
	@PutMapping("/api/wishlist")
	public ResponseEntity<Favorite> update(@RequestBody Favorite favorite){
		return ResponseEntity.ok(favoriteSer.save(favorite));
	}
	
	@DeleteMapping("/api/wishlist/{id}")
	public void delele(@PathVariable("id") Integer id){
		favoriteSer.delete(id);
	}
	
	@GetMapping("/api/addWishlist")
	public ResponseEntity<Favorite> addWishList(@RequestParam("auth") String auth, @RequestParam("idProduct") Integer idProduct) {
		Favorite favorite = new Favorite();
		if(favoriteSer.existsProduct(auth, idProduct) ==null) {
			favorite.setAccount(accSer.getOne(auth));
			favorite.setProduct(prdSer.getOne(idProduct));
			favoriteSer.save(favorite);
		}
		return ResponseEntity.ok(favorite);
	}
	
	@GetMapping("/api/top-product-favorite")
	public ResponseEntity<Object[]> prodFavor() {
		return ResponseEntity.ok(favoriteSer.getPrdFavorited());
	}
}
