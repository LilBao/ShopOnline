package com.shoponline.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shoponline.Entity.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{
	@Query("Select o from Product o where category.name like :namecate ")
	List<Product> getByCondition(@Param("namecate") String name);
}
