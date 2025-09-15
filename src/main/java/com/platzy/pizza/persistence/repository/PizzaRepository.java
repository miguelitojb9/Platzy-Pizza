package com.platzy.pizza.persistence.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.platzy.pizza.persistence.entity.PizzaEntity;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

}
