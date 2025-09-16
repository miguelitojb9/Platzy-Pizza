package com.platzy.pizza.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.platzy.pizza.persistence.entity.PizzaEntity;
import com.platzy.pizza.persistence.repository.PizzaPagSortRepository;
import com.platzy.pizza.persistence.repository.PizzaRepository;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public List<PizzaEntity> getAll(){
        return this.pizzaRepository.findAll();
    }

    public Page<PizzaEntity> getAllPagSort(int page, int elements){
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    // public List<PizzaEntity> getAvailable(){
    //     return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    // }
    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public PizzaEntity getAvailableByName(String name){
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
        .orElseThrow(() -> new RuntimeException("No pizza found with name: " + name));
    }

    public List<PizzaEntity> getCheapest(double price){
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public PizzaEntity get(int id){
        return this.pizzaRepository.findById(id).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }

    public boolean exists(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }

    public void delete(int idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }



    // public List<PizzaEntity> getAll(){
    //     return this.jdbcTemplate.query("SELECT * FROM pizzas WHERE available = 0 ", new BeanPropertyRowMapper<>(PizzaEntity.class));
    // }


}
