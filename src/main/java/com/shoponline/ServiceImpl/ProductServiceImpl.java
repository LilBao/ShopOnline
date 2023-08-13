package com.shoponline.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public Page<Product> getByConditions(String namecate, Integer pageNo, Integer pageSize, String sortField,
			String sortDirection, Integer size, Float minPrice, Float maxPrice) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		return dao.getByConditions(namecate, minPrice, maxPrice, size, pageable);
	}

	@Override
	public Product getNew() {
		return dao.findFirst();
	}

	@Override
	public List<Product> get2Hot() {
		return dao.get2Hot();
	}

	@Override
	public List<Product> getAllNotNull() {
		return dao.findDistinctByProductdetailNotNull();
	}

	@Override
	public List<Product> getHotList(Integer productid) {
		return dao.getAllHot(productid);
	}

	@Override
	public List<Product> getSaleList(Integer productid) {
		return dao.getSale(productid);
	}

	@Override
	public List<Product> getByKeyword(String key) {
		return dao.getByKey(key);
	}

	@Override
	public List<Product> getByCondition(String cate,Integer productid) {
		return dao.getByCondition(cate,productid);
	}
	
}
