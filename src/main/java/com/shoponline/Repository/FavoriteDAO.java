package com.shoponline.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shoponline.Entity.Favorite;

@Repository
public interface FavoriteDAO extends JpaRepository<Favorite, Integer>{
	@Query("Select o from Favorite o where account.username = :username")
	List<Favorite> getByUserName(@Param("username") String username);
	
	@Query("Select o from Favorite o where account.username = :username and product.productid = :productid")
	Favorite getByProductUser(@Param("username") String username, @Param("productid") Integer productid);
}
