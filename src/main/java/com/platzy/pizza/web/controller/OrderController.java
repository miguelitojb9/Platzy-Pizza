package com.platzy.pizza.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platzy.pizza.persistence.entity.OrderEntity;
import com.platzy.pizza.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderEntity> getAll(){
        List<OrderEntity> orders = this.orderService.getAll();
        orders.forEach(o -> System.out.println(o.getCustomer().getName()));
        return orders;
    }

}
