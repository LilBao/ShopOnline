package com.shoponline.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoponline.Entity.Coupon;
import com.shoponline.Repository.VoucherDAO;
import com.shoponline.Service.VoucherService;

@Service
public class VoucherServiceImpl implements VoucherService{
	@Autowired
	VoucherDAO voucherdao;
	
	@Override
	public Coupon save(Coupon coupon) {
		voucherdao.save(coupon);
		return coupon;
	}

	@Override
	public void delete(Integer id) {
		voucherdao.deleteById(id);
	}

	@Override
	public List<Coupon> getAll() {
		return voucherdao.findAll();
	}

	@Override
	public Coupon getOne(Integer id) {
		return voucherdao.findById(id).get();
	}

	@Override
	public Boolean existsById(Integer id) {
		return voucherdao.existsById(id);
	}
	
}
