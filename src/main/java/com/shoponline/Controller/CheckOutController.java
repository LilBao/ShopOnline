package com.shoponline.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckOutController {
	@GetMapping("check-out")
	public String view() {
		return "checkout";
	}
}
