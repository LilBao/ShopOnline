package com.shoponline.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String view(Model model) {
		model.addAttribute("listProduct",productSer.getAll());
		return "categories";
	}
	
	@GetMapping("/product-collections/{cate}")
	public String view2(Model model,@PathVariable("cate") String cate) {
		model.addAttribute("listProduct",productSer.getByCondition(cate));
		//model.addAttribute("listProduct",productSer.getAll());
		return "categories";
	}
}
