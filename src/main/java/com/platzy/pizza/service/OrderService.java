package com.platzy.pizza.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platzy.pizza.persistence.entity.OrderEntity;
import com.platzy.pizza.persistence.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public List<OrderEntity> getAll(){
        return this.orderRepository.findAll();
    }

    public List<OrderEntity> getTodayOrders(){
        LocalDateTime today = LocalDate.now().atTime(0, 0);
        return this.orderRepository.findAllByDateAfter(today);
    
    }
    
    public List<OrderEntity> getOutsideOrders(){
        List<String> methods = List.of(DELIVERY, CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

}
