package com.shoponline.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shoponline.Entity.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
	@Query("Select distinct o from Product o join o.productdetail pd where o.category.name like :namecate and o.status = 1 and (pd.size >= :size or pd.size is null) and o.price between :min and :max")
	Page<Product> getByConditions(@Param("namecate") String namecate, @Param("min") Float min, @Param("max") Float max,
			@Param("size") Integer size, Pageable pagenable);
	
	@Query("Select distinct o from Product o join o.productdetail pd where o.category.name like :namecate and o.status = 1 and pd != null and not o.productid= :prdid ")
	List<Product> getByCondition(@Param("namecate") String namecate,@Param("prdid") Integer prdid);


	List<Product> findDistinctByProductdetailNotNull();

	@Query(value = "Select top 1 * from products order by createdate DESC", nativeQuery = true)
	Product findFirst();

	@Query(value = "select top 2 * from products order by hot DESC ", nativeQuery = true)
	List<Product> get2Hot();

	@Query("select o from Product o where o.hot > GETDATE() and o.status = 1 and not o.productid= :prdid")
	List<Product> getAllHot(@Param("prdid") Integer prdid);

	@Query("select o from Product o where o.promotionprice > 0 and o.status = 1 and not o.productid= :prdid")
	List<Product> getSale(@Param("prdid") Integer prdid);

	@Query("select o from Product o where o.name like :keyword and o.status = 1")
	Page<Product> getByKey(@Param("keyword") String keyword,Pageable pagenable);
}
