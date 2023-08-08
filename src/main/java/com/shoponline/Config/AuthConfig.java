package com.shoponline.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.shoponline.ServiceImpl.UserService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthConfig extends WebSecurityConfigurerAdapter {
	/*--Mã hóa mật khẩu--*/
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	UserService userdetailService;
	/*--Quản lý người dữ liệu người sử dụng--*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userdetailService);
	}
	
	/*--Phân quyền sử dụng và hình thức đăng nhập--*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// CSRF, CORS
		http.csrf().disable().cors().disable();
		
		// Phân quyền sử dụng
		http.authorizeRequests().antMatchers("/settings").permitAll()
							.anyRequest().permitAll();
		
		// Giao diện đăng nhập
		http.formLogin()
			.loginPage("/auth")
			.loginProcessingUrl("/auth/login")
			.defaultSuccessUrl("/index", false)
			.failureUrl("/auth/login/error")
			.usernameParameter("username") 
			.passwordParameter("password"); 
		http.rememberMe()
			.rememberMeParameter("remember");
		
		// Đăng xuất
		http.logout()
			.logoutUrl("/auth/logoff") 
			.logoutSuccessUrl("/index")
			.addLogoutHandler(new SecurityContextLogoutHandler())
			.clearAuthentication(true);;	
		
		
		// Điều khiển lỗi truy cập không đúng vai trò
		http.exceptionHandling().accessDeniedPage("/auth/access/denied");
	}
	
}