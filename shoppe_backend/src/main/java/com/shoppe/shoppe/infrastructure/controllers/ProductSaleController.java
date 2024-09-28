package com.shoppe.shoppe.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.shoppe.shoppe.application.services.IProductSaleService;
import com.shoppe.shoppe.domain.entities.ProductSale;
import com.shoppe.shoppe.domain.entities.ProductSaleId;

public class ProductSaleController {

    @Autowired
    private IProductSaleService productSaleService;

    @GetMapping
    public List<ProductSale> list() {
        return productSaleService.findAll();
    }
    
    @GetMapping("/{productId}/{saleId}")
    public ResponseEntity<?> view(@PathVariable Long productId, @PathVariable Long saleId){
        
        ProductSaleId productSaleId = new ProductSaleId(productId,saleId);

        Optional<ProductSale> productSaleOptional = productSaleService.findById(productSaleId);
        if(productSaleOptional.isPresent()){
            return ResponseEntity.ok(productSaleOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductSale productSale){
        return ResponseEntity.status(HttpStatus.CREATED).body(productSaleService.save(productSale));
    }
    
    // @PutMapping("/{id}")
    // public ResponseEntity<?> update(@RequestBody ProductSale productSale, @PathVariable Long id) {
    //     Optional<ProductSale> productSaleOptional = productSaleService.update(id, productSale);
    //     if (productSaleOptional.isPresent()) {
    //         return ResponseEntity.status(HttpStatus.CREATED).body(productSaleOptional.orElseThrow());
    //     }
    //     return ResponseEntity.notFound().build();
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<?> delete(@PathVariable Long id) {
    //     Optional<ProductSale> productSaleOptional = productSaleService.delete(id);
    //     if (productSaleOptional.isPresent()) {
    //         return ResponseEntity.ok(productSaleOptional.orElseThrow());
    //     }
    //     return ResponseEntity.notFound().build();
    // }
}
