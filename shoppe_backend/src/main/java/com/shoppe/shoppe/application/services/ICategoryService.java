package com.shoppe.shoppe.application.services;

import java.util.List;
import java.util.Optional;

import com.shoppe.shoppe.domain.entities.Category;

public interface ICategoryService {
    List<Category> findAll();

    Optional<Category> findById(Long id);

    Category save(Category category);

    Optional<Category> update(Long id, Category category);

    Optional<Category> delete(Long id);
}
