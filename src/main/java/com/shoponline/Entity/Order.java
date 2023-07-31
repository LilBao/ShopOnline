package com.shoponline.Entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
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
@Table(name="orders")
public class Order {
	@Id
	private Integer orderid=Math.abs(UUID.randomUUID().hashCode());
	private Date orderdate = new Date();
	private Boolean status =false;
	private Boolean delivered = false;
	private Date delivereddate;
	private Float discount;
	
	@ManyToOne
	@JoinColumn(name="username")
	Account account;
	
	private String name;
	private String email;
	private String address;
	private String phone;
	private Float subtotal;
	private Float total;
	private Date createdate = new Date();
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
