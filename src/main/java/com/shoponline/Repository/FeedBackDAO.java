package com.shoponline.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shoponline.Entity.Feedback;

@Repository
public interface FeedBackDAO extends JpaRepository<Feedback, Integer>{
	@Query("select o from Feedback o where product.productid = :productid")
	List<Feedback> getByProduct(@Param("productid") Integer id);
	
	@Query("select count(o) from Feedback o where product.productid = :productid")
	Integer getCountRateProduct(@Param("productid") Integer productid);
	
	@Query("select count(o) from Feedback o where product.productid = :productid and o.rate = :rate")	Integer getStarRating(@Param("productid") Integer productid,@Param("rate") Integer rate);
	
	@Query("select p.productid, p.name, p.thumbnail, p.price ,count(p.productid) from Feedback o join o.product p "
												+ "group by p.productid, p.name, p.thumbnail, p.price,p.productid "
												+ "order by count(p.productid) desc")
	Object[] getRatingFb();
}
