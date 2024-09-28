package com.shoppe.shoppe.infrastructure.repositories.sale;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shoppe.shoppe.domain.entities.Sale;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long> {

}
