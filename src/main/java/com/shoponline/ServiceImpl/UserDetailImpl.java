package com.shoponline.ServiceImpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shoponline.Entity.Account;

@Service
public class UserDetailImpl implements UserDetails {
	Account account;

	public UserDetailImpl(Account account) {
		this.account = account;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return account.getAuthority().stream().map(au -> {return new SimpleGrantedAuthority("ROLE_" + au.getRole().getId());
			}).toList();
	}

	@Override
	public String getPassword() {
		PasswordEncoder pe = new BCryptPasswordEncoder();
		return pe.encode(account.getPassword());
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
