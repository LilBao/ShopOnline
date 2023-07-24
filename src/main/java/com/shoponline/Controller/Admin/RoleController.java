package com.shoponline.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleController {
	@GetMapping("admin-role")
	public String view() {
		return "Admin/Role";
	}
}
