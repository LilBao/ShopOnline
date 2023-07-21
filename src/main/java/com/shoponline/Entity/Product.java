package com.shoponline.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	private String name;
	private Integer status;
	private String thumbnail;
	private String[] listImage;
	private Float price;
	private Float promotion;
	private Boolean vat;
	private Integer warranty;
	private Date hot;
	private String descriptions;
	private String detail;
	private String metakeyword;
	private String metadesciption;
	private String createby;
	private Date createdate;
	private String updateby;
	private Date updatedate;
	
	@ManyToOne
	@JoinColumn(name = "cateid")
	Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<ProductDetail> productdetail;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderdetail;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<ProductComment> productcomment;
}
