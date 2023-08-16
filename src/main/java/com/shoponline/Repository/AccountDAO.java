package com.shoponline.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shoponline.Entity.Account;
import java.util.List;


@Repository
public interface AccountDAO extends JpaRepository<Account, String>{
	@Query("select o from Account o where o.token = :token")
	Account findByToken(@Param("token") String token);
	
	Account findByEmail(String email);
}
