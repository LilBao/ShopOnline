package com.shoponline.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shoponline.Service.AccountService;

@Controller
public class AccountController {
	@Autowired
	AccountService accountSer;
	
	@GetMapping("admin-role")
	public String view() {
		return "Admin/Role";
	}
	
	@GetMapping("admin-account")
	public String viewaccount(Model model) {
		model.addAttribute("listAccount",accountSer.getAll());
		return "Admin/Account";
	}
	@GetMapping("/admin-account/delete/{username}")
	public String delete(@PathVariable("username") String username) {
		accountSer.delete(username);
		return "redirect:/admin-account";
	}
}
