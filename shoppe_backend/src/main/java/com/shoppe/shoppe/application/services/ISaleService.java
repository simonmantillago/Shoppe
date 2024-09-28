package com.shoppe.shoppe.application.services;

import java.util.List;
import java.util.Optional;

import com.shoppe.shoppe.domain.entities.Sale;

public interface ISaleService {
    List<Sale> findAll();

    Optional<Sale> findById(Long id);

    Sale save(Sale sale);

    Optional<Sale> update(Long id, Sale sale);

    Optional<Sale> delete(Long id);
}

