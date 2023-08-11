package com.shoponline.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoponline.Entity.Feedback;
import com.shoponline.Repository.FeedBackDAO;
import com.shoponline.Service.FeedbackService;

@Service
public class FeedbackServiceImp implements FeedbackService{

	@Autowired
	FeedBackDAO dao;
	
	@Override
	public Feedback save(Feedback feedback) {
		dao.save(feedback);
		return feedback;
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Feedback> getAll() {
		return dao.findAll();
	}

	@Override
	public Feedback getOne(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public Boolean existsById(Integer id) {
		return dao.existsById(id);
	}

	@Override
	public List<Feedback> getByUser(String username) {
		return null;
	}

	@Override
	public List<Feedback> getByProduct(Integer id) {
		return dao.getByProduct(id);
	}

	@Override
	public Integer getCountRateProduct(Integer productid) {
		return dao.getCountRateProduct(productid);
	}

	@Override
	public Integer getStarRating(Integer productid, Integer rate) {
		return dao.getStarRating(productid, rate);
	}

	@Override
	public Object[] getRatingFb() {
		return dao.getRatingFb();
	}
	
	
}
