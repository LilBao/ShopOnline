package com.shoponline.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoponline.Entity.Order;
import com.shoponline.Service.OrderService;

@Controller
public class PaymentReturnController {
	@Autowired
	OrderService orderSer;
	
	@GetMapping("payment-online")
	public String view(Model model,@RequestParam("fullname") String fullname,@RequestParam("email") String email,
				@RequestParam("phone") String phone, @RequestParam("address") String address,
				@RequestParam("subtotal") Float subtotal, @RequestParam("discount") Float discount,
				@RequestParam("vnp_Amount") Float amount,@RequestParam("vnp_OrderInfo") String orderInfo,
				@RequestParam("vnp_BankCode") String bankCode,@RequestParam("vnp_PayDate") String payDate,
				@RequestParam("vnp_TxnRef") Integer id,@RequestParam("vnp_BankTranNo") String bankTranNo,
				@RequestParam("vnp_CardType") String cardType,@RequestParam("vnp_TransactionStatus") String transactionStatus
				) throws ParseException, InterruptedException {
		if(transactionStatus.equals("00")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = dateFormat.parse(payDate);
			Order order = new Order();
			order.setName(fullname);
			order.setEmail(email);
            order.setAddress(address);
            order.setPhone(phone);
            
            order.setTotal(amount);
            order.setSubtotal(subtotal);
            order.setDiscount(discount);
            
            order.setContent(orderInfo);
            order.setBankcode(bankCode);
            order.setDatesend(new Date());
            order.setBanktranno(bankTranNo);
            order.setCardtype(cardType);
            order.setPaydate(date);
            orderSer.save(order);
            model.addAttribute("orderid",order.getOrderid());
        }
        return "paymentsuccess";
	}
}
