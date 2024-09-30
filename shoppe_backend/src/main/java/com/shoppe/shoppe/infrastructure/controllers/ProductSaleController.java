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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppe.shoppe.application.services.IProductSaleService;
import com.shoppe.shoppe.domain.entities.ProductSale;
import com.shoppe.shoppe.domain.entities.ProductSaleId;

@RestController
@RequestMapping("/api/productsale")
public class ProductSaleController {

    @Autowired
    private IProductSaleService productSaleService;

    @GetMapping
    public List<ProductSale> list() {
        return productSaleService.findAll();
    }

    @GetMapping("/{productId}/{saleId}")
    public ResponseEntity<?> view(@PathVariable Long productId, @PathVariable Long saleId) {
        ProductSaleId productSaleId = new ProductSaleId(productId, saleId);
        Optional<ProductSale> productSaleOptional = productSaleService.findById(productSaleId);
        if (productSaleOptional.isPresent()) {
            return ResponseEntity.ok(productSaleOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductSale productSale) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productSaleService.save(productSale));
    }

    @PutMapping("/{productId}/{saleId}")
    public ResponseEntity<?> update(@RequestBody ProductSale productSale, @PathVariable Long productId, @PathVariable Long saleId) {
        ProductSaleId productSaleId = new ProductSaleId(productId, saleId);
        Optional<ProductSale> productSaleOptional = productSaleService.findById(productSaleId);
        if (productSaleOptional.isPresent()) {
            productSale.setId(productSaleId);

            return ResponseEntity.ok(productSaleService.save(productSale));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{productId}/{saleId}")
    public ResponseEntity<?> delete(@PathVariable Long productId, @PathVariable Long saleId) {
        ProductSaleId productSaleId = new ProductSaleId(productId, saleId);
        Optional<ProductSale> productSaleOptional = productSaleService.delete(productSaleId);
        if (productSaleOptional.isPresent()) {
            return ResponseEntity.ok(productSaleOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
