package com.shoponline.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shoponline.Service.ProductService;

@Controller()
public class ProductCollectionController {
	@Autowired
	ProductService productSer;
	
	@GetMapping("product-collections")
	public String view(Model model,Authentication auth) {
		if(auth!=null) {
			model.addAttribute("auth",auth.getName());
			}
		model.addAttribute("listProduct",productSer.getAllNotNull());
		return "categories";
	}
	
	@GetMapping("/product-collections/{cate}")
	public String view2(Model model,@PathVariable("cate") String cate,Authentication auth) {
		if(auth!=null) {
			model.addAttribute("auth",auth.getName());
		}
		model.addAttribute("listProduct",productSer.getByCondition(cate));
		return "categories";
	}
}
