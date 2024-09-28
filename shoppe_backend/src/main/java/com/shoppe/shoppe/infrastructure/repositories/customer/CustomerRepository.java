package com.shoppe.shoppe.infrastructure.repositories.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shoppe.shoppe.domain.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
