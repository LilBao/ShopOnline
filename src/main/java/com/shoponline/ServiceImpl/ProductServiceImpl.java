package com.shoponline.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoponline.Entity.Product;
import com.shoponline.Repository.ProductDAO;
import com.shoponline.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDAO dao;
	
	@Override
	public Product save(Product product) {
		dao.save(product);
		return product;
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Product> getAll() {
		return dao.findAll();
	}

	@Override
	public Product getOne(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public Boolean existsById(Integer id) {
		return dao.existsById(id);
	}

	@Override
	public List<Product> getByCondition(String cate) {
		return dao.getByCondition(cate);
	}
	
}
