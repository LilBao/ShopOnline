package com.shoponline.ServiceImpl;

import java.util.Base64;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shoponline.Entity.Account;
import com.shoponline.Service.AccountService;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	AccountService accSer;
	
	@Autowired
	HttpSession session;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {	
		try {
			PasswordEncoder pe = new BCryptPasswordEncoder();
			Account account = accSer.getOne(username);
			// Tạo UserDetails từ Account
			session.setAttribute("avatar", account.getAvatar());
			return new UserDetailImpl(account);
		} catch (Exception e) {
			throw new UsernameNotFoundException(username + "not found");
		}
	}

	
	public void setToken(String username, String password) {
		byte[] auth = (username + ":" + password).getBytes();
		String token = "Basic " + Base64.getEncoder().encodeToString(auth);
		session.setAttribute("token", token);
	}
	
	public String getToken() {
		return (String) session.getAttribute("token");
	}
}
