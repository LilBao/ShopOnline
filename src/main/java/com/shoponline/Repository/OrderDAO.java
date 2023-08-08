package com.shoponline.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shoponline.Entity.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer>{
	@Query("select o from Order o where o.status = :status")
	List<Order> getOrderByStatus(@Param("status") Integer status);
	
	@Query("select o from Order o where account.username = :username")
	List<Order> getOrderByUsername(@Param("username") String username);

}
