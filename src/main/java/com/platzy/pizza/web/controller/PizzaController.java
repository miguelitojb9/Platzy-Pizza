package com.platzy.pizza.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platzy.pizza.persistence.entity.PizzaEntity;
import com.platzy.pizza.service.PizzaService;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll(){
        return ResponseEntity.ok(this.pizzaService.getAll());
    }

    @GetMapping("/pag-sort")
    public ResponseEntity<Page<PizzaEntity>> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int elements){
        return ResponseEntity.ok(this.pizzaService.getAllPagSort(page, elements));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int elements,
        @RequestParam(defaultValue = "price") String sort,
        @RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(this.pizzaService.getAvailable(page, elements, sort, sortDirection));
    }

    @GetMapping("/available/{name}")
    public ResponseEntity<PizzaEntity> getAvailableByName(@PathVariable String name){
        return ResponseEntity.ok(this.pizzaService.getAvailableByName(name));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapestPizzas(@PathVariable double price){
        return ResponseEntity.ok(this.pizzaService.getCheapest(price));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> get(@PathVariable int id){
        return ResponseEntity.ok(this.pizzaService.get(id));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizza){
        if(pizza.getId() == null || !this.pizzaService.exists(pizza.getId())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza){
        if(pizza.getId() != null && this.pizzaService.exists(pizza.getId())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        if(this.pizzaService.exists(id)){
            this.pizzaService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
