package com.shoppe.shoppe.infrastructure.repositories.productsale;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shoppe.shoppe.domain.entities.ProductSale;
import com.shoppe.shoppe.domain.entities.ProductSaleId;

@Repository
public interface ProductSaleRepository extends CrudRepository<ProductSale,ProductSaleId> {
    
}
