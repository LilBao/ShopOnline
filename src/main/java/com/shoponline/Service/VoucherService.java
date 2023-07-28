package com.shoponline.Service;

import java.util.List;

import com.shoponline.Entity.Coupon;

public interface VoucherService {
	Coupon save(Coupon coupon);
	void delete(Integer id);
	List<Coupon> getAll();
	Coupon getOne(Integer id);
	Boolean existsById(Integer id);
}
