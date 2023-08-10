package com.shoponline.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticController {
	
	@GetMapping("admin-statistic")
	public String view() {
		return "statistic";
	}
}
