package com.shoponline.ServiceImpl;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shoponline.Entity.Account;
import com.shoponline.Entity.Email;
import com.shoponline.Service.AccountService;
import com.shoponline.Service.EmailService;
import com.shoponline.Service.ForgotPassService;

@Service
public class ForgotPassServiceImpl implements ForgotPassService{
	@Autowired
	PasswordEncoder pe;
	
	@Autowired
	AccountService accSer;
	
	@Autowired
	EmailService mailService;
	
	@Autowired
	HttpServletRequest req;
	
	@Override
	public void requestPasswordReset(String username) {
		Account account = accSer.getOne(username);
        String token = generatePasswordResetToken(account);
        sendPasswordResetEmail(account.getEmail(), token);
	}

	@Override
	public String generatePasswordResetToken(Account account) {
		String token = UUID.randomUUID().toString();
		account.setToken(token);
        account.setExpiredtoken(LocalDateTime.now().plusMinutes(15));
        accSer.save(account);
		return token;
	}

	@Override
	public void sendPasswordResetEmail(String email, String token) {
		Email mail = new Email();
		mail.setFrom("lilbaozxje@gmail.com");
		mail.setTo(email);
		mail.setSubject("Xác nhận tài khoản từ store");
		String verificationLink ="http://localhost:8080/verification?token=" + token;
		mail.setBody("Truy cập vào đường link sau để nhập mã: <a href=\"" + verificationLink + "\">" + verificationLink + "</a>");
		// Gửi mail
		mailService.enqueue(mail);
	}

	@Override
	public void resetPassword(String token, String newPassword) {
		Account account = accSer.findByToken(token);
        if (account == null || account.getExpiredtoken().isAfter(LocalDateTime.now())) {
            
        }
        account.setPassword(pe.encode(newPassword));
        accSer.save(account);
	}
	
}
