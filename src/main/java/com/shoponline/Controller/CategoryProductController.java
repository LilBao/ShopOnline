package com.shoponline.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class CategoryProductController {
	@GetMapping("product-category")
	public String view() {
		return "categories";
	}
}
