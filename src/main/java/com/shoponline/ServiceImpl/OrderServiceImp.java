package com.shoponline.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
}
