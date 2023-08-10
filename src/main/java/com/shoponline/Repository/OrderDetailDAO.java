package com.shoponline.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shoponline.Entity.OrderDetail;

@Repository
public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer>{
	@Query("Select o from OrderDetail o where order.orderid = :id")
	public List<OrderDetail> getByOrderId(@Param("id") Integer id);
	
	@Query("Select p.productid, p.name, p.price, p.thumbnail, sum(o.quantity) from OrderDetail o join o.product p "
												+ "group by p.productid, p.name, p.price, p.thumbnail, sum(o.quantity) order by sum(o.quantity) desc")
	public List<Object[]> getOrderProduct();
}
