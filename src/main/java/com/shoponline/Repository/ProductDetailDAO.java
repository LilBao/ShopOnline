package com.shoponline.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shoponline.Entity.ProductDetail;

@Repository
public interface ProductDetailDAO extends JpaRepository<ProductDetail, Integer>{
	@Query("Select o from ProductDetail o where o.product.productid = :id")
	List<ProductDetail> getByProductId(@Param("id") Integer id);
	
	@Query("Select o from ProductDetail o where o.product.productid = :id and (o.size is null or o.size = :size)")
	ProductDetail getByProductSize(@Param("id") Integer id, @Param("size") Integer size);
}
