package com.shoponline.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	@GetMapping("auth")
	public String view() {
		return "Auth";
	}
}