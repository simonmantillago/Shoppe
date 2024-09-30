package com.shoppe.shoppe.infrastructure.repositories.productsale;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppe.shoppe.application.services.IProductSaleService;
import com.shoppe.shoppe.domain.entities.ProductSale;
import com.shoppe.shoppe.domain.entities.ProductSaleId;

@Service
public class ProductSaleImpl implements IProductSaleService {

   @Autowired
    private ProductSaleRepository productSaleRepository;

    @Transactional(readOnly =  true)
    @Override
    public List<ProductSale> findAll() {
        return (List<ProductSale>)productSaleRepository.findAll();
    }

    @Transactional(readOnly =  true)
    @Override
    public Optional<ProductSale> findById(ProductSaleId productSaleId) {
        return productSaleRepository.findById(productSaleId);

    }

    @Transactional
    @Override
    public ProductSale save(ProductSale productSale) {
        return productSaleRepository.save(productSale);
    }

    @Transactional
    @Override
    public Optional<ProductSale> update(ProductSaleId productSaleId, ProductSale productSale) {
        Optional<ProductSale> productSaleOld = productSaleRepository.findById(productSaleId);
        if(productSaleOld.isPresent()){
            ProductSale productSaleToUpdate = productSaleRepository.save(productSale);
            productSaleToUpdate.setQuantity(productSale.getQuantity());
            productSaleToUpdate.setStatus(productSale.getStatus());
            productSaleToUpdate.setTotal(productSale.getTotal());
            

           return Optional.of(productSaleRepository.save(productSaleToUpdate));
   
        }

        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<ProductSale> delete(ProductSaleId productSaleId) {
        Optional<ProductSale> productSaleOptional = productSaleRepository.findById(productSaleId);
        productSaleOptional.ifPresent(productSaleDb -> {
            productSaleRepository.delete(productSaleDb);
        }); 
        return productSaleOptional;
    }



}