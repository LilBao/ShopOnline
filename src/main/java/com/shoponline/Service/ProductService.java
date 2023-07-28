package com.shoponline.Service;

import java.util.List;

import com.shoponline.Entity.Product;

public interface ProductService {
	Product save(Product product);
	void delete(Integer id);
	List<Product> getAll();
	Product getOne(Integer id);
	Boolean existsById(Integer id);
}
