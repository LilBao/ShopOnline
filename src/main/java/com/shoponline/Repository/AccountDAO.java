package com.shoponline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoponline.Entity.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account, String>{
	
}
