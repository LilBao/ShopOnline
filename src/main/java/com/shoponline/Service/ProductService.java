package com.shoponline.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.shoponline.Entity.Product;

public interface ProductService {
	Product save(Product product);
	void delete(Integer id);
	List<Product> getAll();
	Product getOne(Integer id);
	Boolean existsById(Integer id);
	Product getNew();
	List<Product> get2Hot();
	List<Product> getAllNotNull();
	List<Product> getHotList(Integer productid);
	List<Product> getSaleList(Integer productid);
	Page<Product> getByKeyword(String key,Integer pageNo, Integer pageSize, String sortField, String sortDirection);
	Page<Product> getByConditions(String cate,Integer pageNo, Integer pageSize, String sortField, String sortDirection, Integer size, Float minPrice, Float maxPrice);
	List<Product> getByCondition(String cate,Integer productid);
}
