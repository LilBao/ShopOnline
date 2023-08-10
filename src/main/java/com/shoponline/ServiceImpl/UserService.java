package com.shoponline.ServiceImpl;

import java.util.Base64;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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
	
	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		PasswordEncoder pe = new BCryptPasswordEncoder();
		String email = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis());
		
		UserDetails user = User.withUsername(email).password(pe.encode(password)).roles("CUS").build();
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
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
