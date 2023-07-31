package com.shoponline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoponline.Entity.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer>{

}