package com.shoponline.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("admin-index")
	public String view() {
		return "Admin/LayoutAdmin";
	}
}
