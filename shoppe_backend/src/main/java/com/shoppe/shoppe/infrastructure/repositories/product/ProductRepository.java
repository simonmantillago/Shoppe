package com.shoppe.shoppe.infrastructure.repositories.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shoppe.shoppe.domain.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
