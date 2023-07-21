package com.shoponline.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orderdetail", uniqueConstraints = {@UniqueConstraint (columnNames = {"orderid", "productid"})})
public class OrderDetail {
	@Id
	@ManyToOne
	@JoinColumn(name="orderid")
	Order order;
	
	@ManyToOne
	@JoinColumn(name="productid")
	Product product;
	
	private String productname;
	private Float price;
	private Integer quantity;
}
