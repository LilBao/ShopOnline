package com.shoponline.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CouponController {
	@GetMapping("admin-coupon")
	public String view () {
		return "Admin/Coupon";
	}
}
