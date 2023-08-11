package com.shoponline.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoponline.Entity.Favorite;
import com.shoponline.Repository.FavoriteDAO;
import com.shoponline.Service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService{

	@Autowired
	FavoriteDAO dao;
	
	@Override
	public Favorite save(Favorite favorite) {
		dao.save(favorite);
		return favorite;
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Favorite> getAll() {
		return dao.findAll();
	}

	@Override
	public Favorite getOne(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public Boolean existsById(Integer id) {
		return dao.existsById(id);
	}

	@Override
	public List<Favorite> getByUser(String username) {
		return dao.getByUserName(username);
	}

	@Override
	public Favorite existsProduct(String username,Integer id) {
		return dao.getByProductUser(username, id);
	}

	@Override
	public Object[] getPrdFavorited() {
		return dao.getPrdFavorited();
	}
	
	
}
