package com.ecommerce.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.PaymentInfo;
import com.ecommerce.dto.Purchase;
import com.ecommerce.dto.PurchaseResponse;
import com.ecommerce.entity.Cliente;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.repository.OrderRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import jakarta.transaction.Transactional;

@Service
public class CheckoutServiceImp implements CheckoutService{

    private CustomerRepository customerRepository;

    private OrderRepository orderRepository;

    public CheckoutServiceImp(CustomerRepository customerRepository, OrderRepository orderRepository, @Value("${stripe.key.secret}") String secretKey){
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        Stripe.apiKey = secretKey;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        
        Order order = purchase.getOrder();

        String orderTrackingNumber = generatedOrderTrackingNumber();
        order.setOrderTracKingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        order.setShippingAddress(purchase.getAddress());


        Cliente customer = purchase.getCliente();

        List<Cliente> customerSaved = customerRepository.findByEmail(customer.getEmail());

        if (customerSaved.size() > 0) {
            customer = customerSaved.get(0);
        }

        customer.add(order);
        order.setCustomer(customer);

        customerRepository.save(customer);
        orderRepository.save(order);

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generatedOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }

    //serve para criar uma intenção de pagamento, onde vamos adicionar as informações que veio do frontend
    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {
        
        List<String> paymentMethodType = new ArrayList<>();
        paymentMethodType.add("card");

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("amount", paymentInfo.getAmount());
        params.put("currency", paymentInfo.getCurrency());
        params.put("receipt_email", paymentInfo.getReceiptEmail());
        params.put("payment_method_types", paymentMethodType);
        params.put("description", "Compra no MundoVariado");

        return PaymentIntent.create(params);
    }
    
}
