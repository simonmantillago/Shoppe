package com.shoppe.shoppe.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppe.shoppe.application.services.ISaleService;
import com.shoppe.shoppe.domain.entities.Sale;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/sale")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @GetMapping
    public List<Sale> list() {
        return saleService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Sale> saleOptional = saleService.findById(id);
        if(saleOptional.isPresent()){
            return ResponseEntity.ok(saleOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Sale sale){

        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.save(sale));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Sale sale, @PathVariable Long id) {
        Optional<Sale> saleOptional = saleService.update(id, sale);
        if (saleOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saleOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Sale> saleOptional = saleService.delete(id);
        if (saleOptional.isPresent()) {
            return ResponseEntity.ok(saleOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}