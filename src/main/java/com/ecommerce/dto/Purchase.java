package com.ecommerce.dto;

import java.util.Set;

import com.ecommerce.entity.Address;
import com.ecommerce.entity.Cliente;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Purchase { 
    private Cliente cliente;
    private Address address;
    private Order order;
    private Set<OrderItem> orderItems;
}
