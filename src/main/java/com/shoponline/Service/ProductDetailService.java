package com.shoponline.Service;

import java.util.List;

import com.shoponline.Entity.ProductDetail;

public interface ProductDetailService {
	ProductDetail save(ProductDetail product);
	void delete(Integer id);
	List<ProductDetail> getAll();
	ProductDetail getOne(Integer id);
	Boolean existsById(Integer id);
	List<ProductDetail> getByProduct(Integer id);
	ProductDetail getByProductSize(Integer id, Integer size);
	Object[] getInventory();
}
