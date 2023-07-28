package com.shoponline.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductDetailController {

	@GetMapping("admin-product-detail")
	public String view() {
		return "Admin/ProductDetail";
	}
}
