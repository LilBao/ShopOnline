package com.shoponline.Controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.shoponline.Entity.Feedback;
import com.shoponline.Entity.Product;
import com.shoponline.Entity.ProductDetail;
import com.shoponline.Service.AccountService;
import com.shoponline.Service.FeedbackService;
import com.shoponline.Service.FileService;
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
	
	@Autowired
	AccountService accSer;
	
	@Autowired
	FileService fileSer;
	
	@Autowired
	FeedbackService feedbackSer;
	
	@GetMapping("product/{id}")
	public String view(Model model,@PathVariable("id") Integer id,Authentication auth,@ModelAttribute("feedback") Feedback feedback) {
		if(auth!=null) {
			model.addAttribute("auth",auth.getName());
			}
		Product prod =  productSer.getOne(id);
		Integer size =req.getParameter("size") == null ? prod.getProductdetail().get(0).getSize() == null ? null : prod.getProductdetail().get(0).getSize() : Integer.valueOf(req.getParameter("size"));
		ProductDetail detail =  productDetailSer.getByProductSize(id,size);
		model.addAttribute("quantity", detail.getQuantity());
		model.addAttribute("idDetail", detail.getId());
		model.addAttribute("product", prod);
		
		model.addAttribute("listRelated", productSer.getByCondition(prod.getCategory().getName(),prod.getProductid()));
		model.addAttribute("listHot", productSer.getHotList(prod.getProductid()));
		model.addAttribute("listSale", productSer.getSaleList(prod.getProductid()));
		model.addAttribute("listFeedback", feedbackSer.getByProduct(prod.getProductid()));
		//star rating
		model.addAttribute("rate", feedbackSer.getCountRateProduct(prod.getProductid()));
		model.addAttribute("rate5", feedbackSer.getStarRating(prod.getProductid(),5));
		model.addAttribute("rate4", feedbackSer.getStarRating(prod.getProductid(),4));
		model.addAttribute("rate3", feedbackSer.getStarRating(prod.getProductid(),3));
		model.addAttribute("rate2", feedbackSer.getStarRating(prod.getProductid(),2));
		model.addAttribute("rate1", feedbackSer.getStarRating(prod.getProductid(),1));
		return "productDetail";
	}
	
	@PostMapping("/feedback/{idProd}")
	public String feedback(Authentication auth,@ModelAttribute("feedback") Feedback feedback,
							@PathVariable("idProd") Integer id,@RequestPart("img") MultipartFile[] files) {
		feedback.setAccount(accSer.getOne(auth.getName()));
		fileSer.upload("feedback", files);
		feedback.setProduct(productSer.getOne(id));
		feedback.setImages(fileSer.upload("feedback", files));
		feedbackSer.save(feedback);
		return "redirect:/product/"+id;
	}
}
