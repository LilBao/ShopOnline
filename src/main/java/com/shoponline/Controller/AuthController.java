package com.shoponline.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shoponline.Entity.Account;
import com.shoponline.Repository.AccountDAO;

@Controller
public class AuthController {
	@Autowired
	AccountDAO accountDao;
	
	@Autowired
	PasswordEncoder pe;
	
	@GetMapping("auth")
	public String view(@ModelAttribute("account") Account account) {
		return "Auth";
	}
	
	@PostMapping("auth-login")
	public String login(@ModelAttribute("account") Account account) {
		Account acc = accountDao.findById(account.getUsername()).get();
		if(pe.matches(account.getPassword(), acc.getPassword())) {
			return "redirect:/index";
		}else {
			return "redirect:/auth";
		}
		
	}
}
