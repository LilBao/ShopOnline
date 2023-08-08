package com.shoponline.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoponline.Entity.ProductDetail;
import com.shoponline.Repository.ProductDetailDAO;
import com.shoponline.Service.ProductDetailService;

@Service
public class ProductDetailServiceImpl implements ProductDetailService{
	
	@Autowired
	ProductDetailDAO dao;
	
	@Override
	public ProductDetail save(ProductDetail product) {	
		dao.save(product);
		return product;
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<ProductDetail> getAll() {
		return dao.findAll();
	}

	@Override
	public ProductDetail getOne(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public Boolean existsById(Integer id) {
		return dao.existsById(id);
	}

	@Override
	public List<ProductDetail> getByProduct(Integer id) {
		return dao.getByProductId(id);
	}

	@Override
	public ProductDetail getByProductSize(Integer id, Integer size) {
		return dao.getByProductSize(id, size);
	}
	
	
}
