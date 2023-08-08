package com.shoponline.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shoponline.Service.AccountService;
import com.shoponline.Service.OrderDetailService;
import com.shoponline.Service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderSer;
	
	@Autowired
	OrderDetailService orderdetailSer;
	
	@GetMapping("admin-orders")
	public String view(Model model) {
		model.addAttribute("listOrderApprove", orderSer.getOrderByStatus(0));
		model.addAttribute("listOrderDecline", orderSer.getOrderByStatus(3));
		return "Admin/Order";
	}
}
