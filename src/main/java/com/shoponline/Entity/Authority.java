package com.shoponline.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="authorities", uniqueConstraints = {@UniqueConstraint (columnNames={"usernameid","roleid"})})
public class Authority {
	@Id
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="usernameid")
	Account account;
	
	@ManyToOne
	@JoinColumn(name="roleid")
	Role role;
}
