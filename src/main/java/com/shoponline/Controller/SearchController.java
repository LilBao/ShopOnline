package com.shoponline.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoponline.Service.ProductService;

@Controller
public class SearchController {
	@Autowired
	ProductService productService;
	
	@GetMapping("/search")
	public String view(Model model,Authentication auth,@RequestParam("keyword") String key) {
		if(auth!=null) {
			model.addAttribute("auth",auth.getName());
			}
		model.addAttribute("listProduct",productService.getByKeyword("%"+key+"%"));
		return "Search";
	}
}
