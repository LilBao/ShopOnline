package com.shoponline.Service;

import java.util.List;

import com.shoponline.Entity.Favorite;

public interface FavoriteService {
	Favorite save(Favorite favorite);
	void delete(Integer id);
	List<Favorite> getAll();
	Favorite getOne(Integer id);
	Boolean existsById(Integer id);
	List<Favorite> getByUser(String username);
	Favorite existsProduct(String username,Integer id);
}
