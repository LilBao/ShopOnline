package com.shoponline.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="productdetail")
public class ProductDetail {
	@Id
	private Integer id;
	private Integer quantity;
	private Integer size;
	
	@ManyToOne
	@JoinColumn(name = "productid")
	Product product;
}
