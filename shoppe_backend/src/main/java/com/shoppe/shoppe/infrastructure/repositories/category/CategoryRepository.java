package com.shoppe.shoppe.infrastructure.repositories.category;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shoppe.shoppe.domain.entities.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
