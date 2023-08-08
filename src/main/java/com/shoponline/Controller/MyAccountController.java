package com.shoponline.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoponline.Entity.Account;
import com.shoponline.Entity.Favorite;
import com.shoponline.Service.AccountService;
import com.shoponline.Service.FavoriteService;
import com.shoponline.Service.OrderService;
import com.shoponline.Service.ProductService;

@Controller
public class MyAccountController {
	@Autowired
	OrderService orderSer;
	
	@Autowired
	AccountService accSer;
	
	@Autowired
	ProductService prdSer;
	
	@Autowired
	PasswordEncoder pe;
	
	@Autowired
	FavoriteService favoriteSer;
	
	@GetMapping("/settings")
	public String view (Model model, Authentication auth) {
		model.addAttribute("auth",auth.getName());
		model.addAttribute("account",accSer.getOne(auth.getName()));
		model.addAttribute("listOrder", orderSer.getOrderByUsername(auth.getName()));
		model.addAttribute("listFavor", favoriteSer.getByUser(auth.getName()));
		return "myaccount";
	}
	
	@GetMapping("/change-pass")
	public String changePass(Model model,Authentication auth, @RequestParam("password") String password,
							@RequestParam("newpass") String newpass,@RequestParam("cfpass") String cfpass) {
		Account account = accSer.getOne(auth.getName());
		if(pe.matches(password, account.getPassword())){
			if(newpass.equals(cfpass)) {
				account.setPassword(pe.encode(newpass));
				accSer.save(account);
				return "redirect:/auth/logoff";
			}
			model.addAttribute("error","Mật khẩu xác nhận và mật khẩu mới không khớp");
			return "forward:/settings";
		}else {
			model.addAttribute("error","Sai mật khẩu");
			return "forward:/settings";
		}
	}
	
}
