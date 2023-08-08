package com.shoponline.Service;

import java.util.List;

import com.shoponline.Entity.Order;

public interface OrderService {
	Order save(Order order);
	void delete(Integer id);
	List<Order> getAll();
	Order getOne(Integer id);
	Boolean existsById(Integer id);
	List<Order> getOrderByUsername(String username);
	List<Order> getOrderByStatus(Integer status);
}
