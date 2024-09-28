package com.shoppe.shoppe.infrastructure.repositories.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppe.shoppe.application.services.ICustomerService;
import com.shoppe.shoppe.domain.entities.Customer;

@Service
public class CustomerImpl implements ICustomerService {

   @Autowired
    private CustomerRepository customerRepository;

    @Transactional(readOnly =  true)
    @Override
    public List<Customer> findAll() {
        return (List<Customer>)customerRepository.findAll();
    }

    @Transactional(readOnly =  true)
    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);

    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    @Override
    public Optional<Customer> update(Long id, Customer customer) {
        Optional<Customer> customerOld = customerRepository.findById(id);
        if(customerOld.isPresent()){
           Customer customerDb =  customerOld.orElseThrow();  
           
           customerDb.setPhone(customer.getPhone());
           customerDb.setName(customer.getName());
           customerDb.setLastName(customer.getLastName());
           customerDb.setEmail(customer.getEmail());
           customerDb.setAddress(customer.getAddress());
           
           return Optional.of(customerRepository.save(customerDb));
        }

        return Optional.empty();
    }

    @Transactional(readOnly =  true)
    @Override
    public Optional<Customer> delete(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        customerOptional.ifPresent(customerDb -> {
            customerRepository.delete(customerDb);
        }); 
        return customerOptional;
    }

}