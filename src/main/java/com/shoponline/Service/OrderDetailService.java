package com.shoponline.Service;

import java.util.List;

import com.shoponline.Entity.OrderDetail;

public interface OrderDetailService {
	OrderDetail save(OrderDetail orderDetail);
	void delete(Integer id);
	List<OrderDetail> getAll();
	OrderDetail getOne(Integer id);
	Boolean existsById(Integer id);
	List<OrderDetail> getByOrderId(Integer id);
}
