package com.peoplezone.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PaymentController {
	private static Gson gson = new Gson();
	
	

    static final String YOUR_DOMAIN = "http://localhost:4242";
	
	@PostMapping("create-checkout-session")
	public String checkout() {
			Stripe.apiKey = "sk_test_Gxp1mCwSFa149BNJXhp2wI07";
		
	        SessionCreateParams params =
	          SessionCreateParams.builder()
	            .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
	            .setMode(SessionCreateParams.Mode.PAYMENT)
	            .setSuccessUrl(YOUR_DOMAIN + "/payment/success")
	            .setCancelUrl(YOUR_DOMAIN + "/payment/cancel")
	            .addLineItem(
	              SessionCreateParams.LineItem.builder()
	                .setQuantity(1L)
	                .setPriceData(
	                  SessionCreateParams.LineItem.PriceData.builder()
	                    .setCurrency("usd")
	                    .setUnitAmount(2000L)
	                    .setProductData(
	                      SessionCreateParams.LineItem.PriceData.ProductData.builder()
	                        .setName("Stubborn Attachments")
	                        .build())
	                    .build())
	                .build())
	            .build();
	      Session session = null;
		try {
			session = Session.create(params);
		} catch (StripeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      HashMap<String, String> responseData = new HashMap<String, String>();
	      responseData.put("id", session.getId());
	      return gson.toJson(responseData);
	}
	
}
