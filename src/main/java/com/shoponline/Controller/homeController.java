package com.shoponline.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shoponline.Service.ProductService;

@Controller
public class homeController {
	@Autowired
	ProductService productSer;
	
	@GetMapping("index")
	public String view(Model model, Authentication auth) {
		if(auth!=null) {
		model.addAttribute("auth",auth.getName());
		}
		model.addAttribute("product",productSer.getNew());
		model.addAttribute("hot",productSer.get2Hot());
		return "index";
	}
}
