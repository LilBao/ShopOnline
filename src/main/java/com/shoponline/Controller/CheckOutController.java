package com.shoponline.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shoponline.Service.AccountService;

@Controller
public class CheckOutController {
	@Autowired
	AccountService accSer;
	
	@GetMapping("check-out")
	public String view(Model model, Authentication auth) {
		if(auth!=null) {
			model.addAttribute("account",accSer.getOne(auth.getName()));
		}
		return "checkout";
	}
}
