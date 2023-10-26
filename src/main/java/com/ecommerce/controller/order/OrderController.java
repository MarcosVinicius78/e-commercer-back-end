package com.ecommerce.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Order;
import com.ecommerce.service.OrderService.OrderSrviceImp;

@RestController
public class OrderController {

    @Autowired
    private OrderSrviceImp orderSrviceImp;

    @GetMapping("/api/order")
    public List<Order> getOrder(@RequestParam String email) {

        return this.orderSrviceImp.getOrder(email);
    }
}
