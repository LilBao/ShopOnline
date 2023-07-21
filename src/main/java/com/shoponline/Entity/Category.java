package com.shoponline.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
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
@Table(name="categories")
public class Category {
	private Integer cateid;
	private String name;
	private Boolean status;
	private Integer sort;
	private Integer parentid;
	private String metakeyword;
	private String metadesciption;
	private String createby;
	private Date createdate;
	private String updateby;
	private Date updatedate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<Product> products;
}
