package com.shoponline.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="coupon")
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String type;
	private Float value;
	private Boolean status=false;
	private Integer quantity=Integer.MAX_VALUE-1000;
	@Temporal(TemporalType.DATE)
	private Date dateend;
	private String createby;
	@Temporal(TemporalType.DATE)
	private Date createdate = new Date();
	private String updateby;
	private Date updatedate;
}
