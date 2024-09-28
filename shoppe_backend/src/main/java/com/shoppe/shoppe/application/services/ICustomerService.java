package com.shoppe.shoppe.application.services;

import java.util.List;
import java.util.Optional;

import com.shoppe.shoppe.domain.entities.Customer;

public interface ICustomerService {
    List<Customer> findAll();

    Optional<Customer> findById(Long id);

    Customer save(Customer customer);

    Optional<Customer> update(Long id, Customer customer);

    Optional<Customer> delete(Long id);
}
