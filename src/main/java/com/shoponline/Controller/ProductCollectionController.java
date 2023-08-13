package com.shoponline.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoponline.Entity.Product;
import com.shoponline.Service.ProductService;

@Controller()
public class ProductCollectionController {
	@Autowired
	ProductService productSer;

	@GetMapping("/product-collections")
	public String view2(Model model, @RequestParam("cate") Optional<String> cate, Authentication auth,
			@RequestParam("pageNo") Optional<Integer> pageNo, @RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("sortField") Optional<String> sortField, @RequestParam("sortDir") Optional<String> sortDir,
			@RequestParam("min") Optional<Float> min,@RequestParam("max") Optional<Float> max,
			@RequestParam("size") Optional<Integer> size) {
		if (auth != null) {
			model.addAttribute("auth", auth.getName());
		}
		
		Page<Product> pageList = productSer.getByConditions(cate.orElse("%"),pageNo.orElse(0), pageSize.orElse(8), sortField.orElse("name"),sortDir.orElse("desc"), size.orElse(7), min.orElse(0.0f),max.orElse(300000000.0f));
		List<Product> list = pageList.getContent();
		model.addAttribute("listProduct", list);
	
		model.addAttribute("cate", cate.orElse("%"));
		model.addAttribute("pageSize", pageSize.orElse(8));
		model.addAttribute("min",  min.orElse(0.0f));
		model.addAttribute("max", max.orElse(300000000.0f));
		model.addAttribute("size", size.orElse(7));
		model.addAttribute("pageNo", pageNo.orElse(0));
		model.addAttribute("totalItems", pageList.getTotalElements());
		model.addAttribute("totalPages", pageList.getTotalPages());
		model.addAttribute("sortField", sortField.orElse("name"));
		model.addAttribute("sortDir", sortDir.orElse("desc"));
		return "categories";
	}
}
