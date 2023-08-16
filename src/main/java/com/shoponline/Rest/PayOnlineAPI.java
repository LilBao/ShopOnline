package com.shoponline.Rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shoponline.Config.PayOnlineConfig;
import com.shoponline.Entity.Payment;


@RestController
public class PayOnlineAPI {
	@Autowired
	HttpServletRequest req;
	
	@Autowired
	HttpServletResponse resp;
	
	@GetMapping("/api/payment")
	public Payment payment(@RequestParam("total") long total,@RequestParam("fullname") String name, @RequestParam("email") String email,
								@RequestParam("phone") String phone, @RequestParam("address") String address,@RequestParam("code") String code,
								@RequestParam("subtotal") Float subtotal, @RequestParam("discount") Float discount) throws UnsupportedEncodingException {
		String vnp_Version = PayOnlineConfig.vnp_Version;
        String vnp_Command = PayOnlineConfig.vnp_Command;
//        String orderType = req.getParameter("ordertype");
        long amount = total*100;
        
        String vnp_TxnRef = PayOnlineConfig.getRandomNumber(8);
        String vnp_IpAddr = PayOnlineConfig.getIpAddress(req);
        String vnp_TmnCode = PayOnlineConfig.vnp_TmnCode;
        
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");  
        vnp_Params.put("vnp_BankCode", "");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", PayOnlineConfig.vnp_Returnurl+"?fullname="+name+"&phone="+phone+"&address="+address+"&email="+email+"&subtotal="+subtotal+"&discount="+discount+"&code="+code);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = PayOnlineConfig.hmacSHA512(PayOnlineConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = PayOnlineConfig.vnp_PayUrl + "?" + queryUrl;
        
        Payment payment = new Payment();
        payment.setStatus("00");
        payment.setMessage("Successfully");
        payment.setURL(paymentUrl);
        return payment;
	}
}
