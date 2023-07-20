package com.shoponline.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductDetailController {
	@GetMapping("product-detail")
	public String view() {
		return "productDetail";
	}
}
