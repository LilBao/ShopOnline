package com.shoponline.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name="accounts")
public class Account {
	@Id
	private String username;
	private String password;
	private String fullname;
	private String email;
	private Date birthday;
	private String avatar;
	private String address;
	private String phone;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Authority> authority;
}
