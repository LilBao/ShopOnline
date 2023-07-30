package com.shoponline.Controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shoponline.Entity.Product;
import com.shoponline.Entity.ProductDetail;
import com.shoponline.Service.ProductDetailService;
import com.shoponline.Service.ProductService;

@Controller
public class DetailPrdController {
	@Autowired
	ProductService productSer;
	
	@Autowired
	ProductDetailService productDetailSer;
	
	@Autowired
	HttpServletRequest req;
	
	@GetMapping("product/{id}")
	public String view(Model model,@PathVariable("id") Integer id) {
		Product prod =  productSer.getOne(id);
		Integer size =req.getParameter("size") == null ? prod.getProductdetail().get(0).getSize() == null ? null : prod.getProductdetail().get(0).getSize() : Integer.valueOf(req.getParameter("size"));
		ProductDetail detail =  productDetailSer.getByProductSize(id,size);
		model.addAttribute("quantity", detail.getQuantity());
		model.addAttribute("idDetail", detail.getId());
		model.addAttribute("product", prod);
		return "productDetail";
	}
}
