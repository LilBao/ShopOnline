package com.shoponline.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoponline.Entity.Product;
import com.shoponline.Service.ProductService;

@Controller
public class SearchController {
	@Autowired
	ProductService productService;

	@GetMapping("/search")
	public String view(Model model, Authentication auth, @RequestParam("keyword") String key,
			@RequestParam("pageNo") Optional<Integer> pageNo, @RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("sortField") Optional<String> sortField, @RequestParam("sortDir") Optional<String> sortDir) {
		if (auth != null) {
			model.addAttribute("auth", auth.getName());
		}
		Page<Product> pageList = productService.getByKeyword("%" + key + "%",pageNo.orElse(0), pageSize.orElse(8), sortField.orElse("name"),sortDir.orElse("desc"));
		List<Product> list = pageList.getContent();
		model.addAttribute("key", key);
		model.addAttribute("listProduct", list);
		model.addAttribute("pageSize", pageSize.orElse(8));
		model.addAttribute("pageNo", pageNo.orElse(0));
		model.addAttribute("totalItems", pageList.getTotalElements());
		model.addAttribute("totalPages", pageList.getTotalPages());
		model.addAttribute("sortField", sortField.orElse("name"));
		model.addAttribute("sortDir", sortDir.orElse("desc"));
		return "Search";
	}
}
