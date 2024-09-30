package com.shoppe.shoppe.infrastructure.repositories.sale;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppe.shoppe.application.services.ISaleService;
import com.shoppe.shoppe.domain.entities.Sale;

@Service
public class SaleImpl implements ISaleService {

   @Autowired
    private SaleRepository productRepository;

    @Transactional(readOnly =  true)
    @Override
    public List<Sale> findAll() {
        return (List<Sale>)productRepository.findAll();
    }

    @Transactional(readOnly =  true)
    @Override
    public Optional<Sale> findById(Long id) {
        return productRepository.findById(id);

    }

    @Transactional
    @Override
    public Sale save(Sale product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Sale> update(Long id, Sale product) {
        Optional<Sale> productOld = productRepository.findById(id);
        if(productOld.isPresent()){
           Sale productDb =  productOld.orElseThrow();  
           
           productDb.setCustomer(productDb.getCustomer());
           productDb.setPaymentMethod(productDb.getPaymentMethod());
           productDb.setComment(productDb.getComment());
           productDb.setStatus(productDb.getStatus());

           return Optional.of(productRepository.save(productDb));
        }

        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Sale> delete(Long id) {
        Optional<Sale> productOptional = productRepository.findById(id);
        productOptional.ifPresent(productDb -> {
            productRepository.delete(productDb);
        }); 
        return productOptional;
    }

}