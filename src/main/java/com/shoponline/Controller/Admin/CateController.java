package com.shoponline.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CateController {
	@GetMapping("admin-category")
	public String view() {
		return "Admin/Category";
	}
}
