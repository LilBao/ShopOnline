package com.shoponline.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoponline.Entity.Account;
import com.shoponline.Repository.AccountDAO;
import com.shoponline.Service.AccountService;

@Controller
public class AuthController {
	@Autowired
	AccountService accountSer;	
	
	@RequestMapping("/auth")
	public String viewlogin(Model model,@RequestParam("error") Optional<String> error) {
		model.addAttribute("message", error.orElse(""));
		return "Auth";
	}
	
	@GetMapping("/auth/login/error")
	public String error(Model model){
		model.addAttribute("message", "");
		return "redirect:/auth?error=Check your username or password!";
	}

	@RequestMapping("/auth/access/denied")
	public String denied(Model model){
		return "redirect:/auth?error=Bạn không có quyền truy xuất!";
	}
}
