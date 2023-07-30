package com.shoponline.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class ProductDetail implements Cloneable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer quantity;
	private Integer size;
	
	@ManyToOne
	@JoinColumn(name = "productid")
	Product product;
	
	public ProductDetail order(Integer quantity) {
		ProductDetail prod = null;
		try {
			prod = (ProductDetail) this.clone();
			prod.setQuantity(quantity);
			this.quantity-=quantity;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return prod;
	}
}
