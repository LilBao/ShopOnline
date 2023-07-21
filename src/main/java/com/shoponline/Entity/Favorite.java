package com.shoponline.Entity;

import javax.persistence.Entity;
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
@Table(name="favorite", uniqueConstraints = {@UniqueConstraint (columnNames= {"username","productid"})})
public class Favorite {
	private Integer id;
	@ManyToOne
	@JoinColumn(name="username")
	Account account;
	
	@ManyToOne
	@JoinColumn(name="productid")
	Product product;
	private String reviews;
}
