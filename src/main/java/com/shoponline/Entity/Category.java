package com.shoponline.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cateid;
	private String name;
	private Boolean status = true;
	private Integer sort;
	private Integer parentid;
	private String metakeyword;
	private String metadescription;
	private String createby;
	@Temporal(TemporalType.DATE)
	private Date createdate = new Date();
	private String updateby;
	private Date updatedate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<Product> products;
}
