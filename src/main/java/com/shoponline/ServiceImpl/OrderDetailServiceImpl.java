package com.shoponline.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoponline.Entity.OrderDetail;
import com.shoponline.Repository.OrderDetailDAO;
import com.shoponline.Service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired
	OrderDetailDAO dao;
	
	@Override
	public OrderDetail save(OrderDetail orderDetail) {
		dao.save(orderDetail);
		return orderDetail;
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<OrderDetail> getAll() {
		return dao.findAll();
	}

	@Override
	public OrderDetail getOne(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public Boolean existsById(Integer id) {
		return dao.existsById(id);
	}

	@Override
	public List<OrderDetail> getByOrderId(Integer id) {
		return dao.getByOrderId(id);
	}
	
}
