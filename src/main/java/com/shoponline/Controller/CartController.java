package com.shoponline.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
	@GetMapping("cart")
	public String view(Model model,Authentication auth) {
		if(auth!=null) {
			model.addAttribute("auth",auth.getName());
		}
		return "cart";
	}
}
