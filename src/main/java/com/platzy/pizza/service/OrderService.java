package com.platzy.pizza.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platzy.pizza.persistence.entity.OrderEntity;
import com.platzy.pizza.persistence.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public List<OrderEntity> getAll(){
        return this.orderRepository.findAll();
    }

}
