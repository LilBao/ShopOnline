package com.shoponline.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private Integer id;
	private String code;
	private String type;
	private Float value;
	private Boolean status;
	private Integer quantity;
	private Date dateend;
	private String createby;
	private Date createdate;
	private String updateby;
	private Date updatedate;
}
