package com.shoponline.Entity;

import java.util.Date;

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
@Table(name="feedback")
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentid;
	private String name;
	private String email;
	private String detail;
	private String[] images;
	private Integer rate;
	private Boolean status = true;
	private Date createdate = new Date();
	private Date updatedate;
	@ManyToOne
	@JoinColumn(name="productid")
	Product product;
	
	@ManyToOne
	@JoinColumn(name="createby")
	Account account;
	
}
