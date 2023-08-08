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
}
