package com.shoponline.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoponline.Service.AccountService;
import com.shoponline.ServiceImpl.UserService;

@Controller
public class AuthController {
	@Autowired
	AccountService accountSer;	
	
	@Autowired
	UserService userdetailService;
	
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
		return "redirect:/auth?error=Your are not permission!";
	}
	
	@GetMapping("/oauth2/login")
	public String form() {
		return "oauth2/authorization";
	}
	
	@GetMapping("/oauth2/login/success")
	public String successOAuth(OAuth2AuthenticationToken token) {
		userdetailService.loginFromOAuth2(token);
		return "redirect:/index";
	}
}
