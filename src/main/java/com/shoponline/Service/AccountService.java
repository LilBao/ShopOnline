package com.shoponline.Service;

import java.util.List;

import com.shoponline.Entity.Account;

public interface AccountService {
	Account save(Account account);
	void delete(String username);
	List<Account> getAll();
	Account getOne(String username);
	Boolean existsById(String username);
	Account findByToken(String token);
	Account findByEmail(String email);
}
