package com.shoponline.Service;

import java.util.List;

import com.shoponline.Entity.Feedback;

public interface FeedbackService {
	Feedback save(Feedback feedback);
	void delete(Integer id);
	List<Feedback> getAll();
	Feedback getOne(Integer id);
	Boolean existsById(Integer id);
	List<Feedback> getByUser(String username);
	List<Feedback> getByProduct(Integer id);
	Integer getCountRateProduct(Integer productid);
	Integer getStarRating(Integer productid,Integer rate);
}
