package com.ecommerce.service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Order;
import com.ecommerce.repository.OrderRepository;

@Service
public class OrderSrviceImp {
    
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrder(String email){
        List<Order> order = this.orderRepository.findByCustomerEmail(email);
        System.out.println(order.toString());
        return order;
    }
}
