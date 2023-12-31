package com.shoponline.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="accounts")
@Component
public class Account implements Serializable{
	@Id
	private String username;
	private String password;
	private String fullname;
	private String email;
	@Temporal(TemporalType.DATE)
	private Date birthday;
	private String avatar;
	private String address;
	private String phone;
	private String token;
	private LocalDateTime expiredtoken;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
	List<Authority> authority;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> order;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Feedback> feedback;
}
