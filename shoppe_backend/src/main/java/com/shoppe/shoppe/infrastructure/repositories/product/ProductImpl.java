package com.shoppe.shoppe.infrastructure.repositories.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppe.shoppe.application.services.IProductService;
import com.shoppe.shoppe.domain.entities.Product;

@Service
public class ProductImpl implements IProductService {

   @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly =  true)
    @Override
    public List<Product> findAll() {
        return (List<Product>)productRepository.findAll();
    }

    @Transactional(readOnly =  true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);

    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productOld = productRepository.findById(id);
        if(productOld.isPresent()){
           Product productDb =  productOld.orElseThrow();  
           
           productDb.setName(product.getName());
           productDb.setCategory(product.getCategory());
           productDb.setBarCode(product.getBarCode());
           productDb.setSalePrice(product.getSalePrice());
           productDb.setStock(product.getStock());
           productDb.setStatus(product.getStatus());
           
           return Optional.of(productRepository.save(productDb));
        }

        return Optional.empty();
    }

    @Transactional(readOnly =  true)
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        productOptional.ifPresent(productDb -> {
            productRepository.delete(productDb);
        }); 
        return productOptional;
    }

}