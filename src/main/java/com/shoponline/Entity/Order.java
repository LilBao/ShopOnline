package com.shoponline.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="orders")
public class Order {
	private Integer orderid;
	private Date orderdate;
	private Boolean status;
	private Boolean delivered;
	private Date deliveredDate;
	private Float discount;
	
	@ManyToOne
	@Column(name="username")
	Account account;
	
	private String name;
	private String email;
	private String address;
	private String phone;
	private Float subtotal;
	private Float total;
	private Date createdate;
	private String updateby;
	private Date updatedate;
	private String content;
	private String bankcode;
	private Date datesend;
	private String banktranno;
	private String cardtype;
	private Date paydate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "order")
	List<OrderDetail> orderdetail;
}
