package com.shoponline.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoponline.Entity.Account;
import com.shoponline.Repository.AccountDAO;
import com.shoponline.Service.AccountService;

@Service
public class AccountServiceImp implements AccountService{
	@Autowired
	AccountDAO dao;
	
	@Override
	public Account save(Account account) {
		dao.save(account);
		return account;
	}

	@Override
	public void delete(String username) {
		dao.deleteById(username);
	}

	@Override
	public List<Account> getAll() {
		return dao.findAll();
	}

	@Override
	public Account getOne(String username) {
		return dao.findById(username).get();
	}

	@Override
	public Boolean existsById(String username) {
		return dao.existsById(username);
	}
	
}
