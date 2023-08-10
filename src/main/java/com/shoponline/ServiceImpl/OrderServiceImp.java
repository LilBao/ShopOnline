package com.shoponline.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shoponline.Entity.Order;
import com.shoponline.Repository.OrderDAO;
import com.shoponline.Service.OrderService;

@Service
public class OrderServiceImp implements OrderService{
	@Autowired
	OrderDAO dao;
	
	@Override
	public Order save(Order order) {
		dao.save(order);
		return order;
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Order> getAll() {
		return dao.findAll();
	}

	@Override
	public Order getOne(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public Boolean existsById(Integer id) {
		return dao.existsById(id);
	}

	@Override
	public List<Order> getOrderByUsername(String username) {
		return dao.getOrderByUsername(username);
	}

	@Override
	public List<Order> getOrderByStatus(Integer status) {
		return dao.getOrderByStatus(status);
	}

	@Override
	public Float getEarningMoth() {
		return dao.getEarningMonth();
	}

	@Override
	public Float getEarningYear() {
		return dao.getEarningAnnual();
	}

	@Override
	public Integer getPendingRequest() {
		return dao.getPendingRequest();
	}
	
	public Page<Object[]> getTop5(){
		Pageable pageable = PageRequest.of(0, 5);
		return dao.getTop5(pageable);
	}
}
