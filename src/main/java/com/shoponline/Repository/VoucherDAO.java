package com.shoponline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shoponline.Entity.Coupon;

@Repository
public interface VoucherDAO extends JpaRepository<Coupon, Integer>{
	@Query("Select o from Coupon o where o.code = :code")
	Coupon getCouponByCode(@Param("code") String code);
}
