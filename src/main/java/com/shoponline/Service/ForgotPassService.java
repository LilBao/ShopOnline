package com.shoponline.Service;

import com.shoponline.Entity.Account;

public interface ForgotPassService {
	public void requestPasswordReset(String username);
	public String generatePasswordResetToken(Account account);
	void sendPasswordResetEmail(String email, String token);
	void resetPassword(String token, String newPassword);
}
