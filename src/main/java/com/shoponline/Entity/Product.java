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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products")
public class Product{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productid;
	private String name;
	private Integer status = 1;
	private String thumbnail;
	private String[] listimage;
	private Float price;
	private Integer promotionprice=0;
	private Boolean vat = false;
	private Integer warranty;
	@Temporal(TemporalType.DATE)
	private Date hot;
	private String descriptions;
	private String detail;
	private String metakeyword;
	private String metadescription;
	private String createby;
	@Temporal(TemporalType.DATE)
	private Date createdate = new Date();
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
