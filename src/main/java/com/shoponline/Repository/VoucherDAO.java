package com.shoponline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoponline.Entity.Coupon;

@Repository
public interface VoucherDAO extends JpaRepository<Coupon, Integer>{

}
