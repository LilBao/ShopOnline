package com.shoponline.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
	@GetMapping("admin-product")
	public String view() {
		return "Admin/Product";
	}
}
