package com.shoponline.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoponline.Entity.Email;
import com.shoponline.Entity.Order;
import com.shoponline.Service.EmailService;
import com.shoponline.Service.OrderService;
import com.shoponline.Service.ThymeleafService;

@RestController
public class SendMailRest {
	@Autowired
	EmailService mailService;
	
	@Autowired
	ThymeleafService thymeleafSer;
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("api/email/{orderid}")
	public void sendMail(@PathVariable("orderid") Integer orderid) {
		Email mail = new Email();
		Order order = orderService.getOne(orderid);
		mail.setFrom(".com");
		mail.setTo(order.getEmail());
		mail.setSubject("CHROME HEART: HÓA ĐƠN MUA HÀNG");
		mail.setBody(thymeleafSer.getContent(order));
		//covert MultipartFile to File
		//Gửi mail
		mailService.enqueue(mail);
	}
	
	@PostMapping("api/approve-order/{orderid}")
	public void sendMailApprove(@PathVariable("orderid") Integer orderid) {
		Email mail = new Email();
		Order order = orderService.getOne(orderid);
		mail.setFrom(".com");
		mail.setTo(order.getEmail());
		mail.setSubject("CHROME HEART: ĐƠN HÀNG CỦA BẠN ĐÃ ĐƯỢC XÁC NHẬN");
//		mail.setBody(thymeleafSer.getContent(order));
		//covert MultipartFile to File
		//Gửi mail
		mailService.enqueue(mail);
	}
	
	@PostMapping("api/cancel-order/{orderid}")
	public void sendMailCancel(@PathVariable("orderid") Integer orderid) {
		Email mail = new Email();
		Order order = orderService.getOne(orderid);
		mail.setFrom(".com");
		mail.setTo(order.getEmail());
		mail.setSubject("CHROME HEART: ĐƠN HÀNG "+order.getOrderid() +" ĐÃ ĐƯỢC HỦY");
//		mail.setBody(thymeleafSer.getContent(order));
		//covert MultipartFile to File
		//Gửi mail
		mailService.enqueue(mail);
	}
}
