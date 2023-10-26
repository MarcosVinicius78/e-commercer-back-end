package com.ecommerce.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.PaymentInfo;
import com.ecommerce.dto.Purchase;
import com.ecommerce.dto.PurchaseResponse;
import com.ecommerce.service.CheckoutService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@RestController
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;
    
    @PostMapping("/api/checkout")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase){
        
        PurchaseResponse purchaseResponse = this.checkoutService.placeOrder(purchase);

        return purchaseResponse;
    }

    @PostMapping("/api/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException{
        PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);

        String paymentStr = paymentIntent.toJson();

        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }
}
