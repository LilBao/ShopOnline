package com.shoponline.Controller.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shoponline.Entity.Order;
import com.shoponline.Service.OrderService;

@Controller
public class IndexController {
	@Autowired
	OrderService orderService;
	
	@GetMapping("admin-index")
	public String view(Model model) {
		model.addAttribute("earningYear", orderService.getEarningYear());
		model.addAttribute("earningMonth", orderService.getEarningMoth());
		model.addAttribute("pending", orderService.getPendingRequest());
		return "Admin/LayoutAdmin";
	}
}
