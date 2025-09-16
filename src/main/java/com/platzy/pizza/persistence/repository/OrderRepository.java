package com.platzy.pizza.persistence.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.platzy.pizza.persistence.entity.OrderEntity;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

}
