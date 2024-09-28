package com.shoppe.shoppe.infrastructure.repositories.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppe.shoppe.application.services.ICategoryService;
import com.shoppe.shoppe.domain.entities.Category;

@Service
public class CategoryImpl implements ICategoryService {

   @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly =  true)
    @Override
    public List<Category> findAll() {
        return (List<Category>)categoryRepository.findAll();
    }

    @Transactional(readOnly =  true)
    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);

    }

    @Transactional
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public Optional<Category> update(Long id, Category category) {
        Optional<Category> categoryOld = categoryRepository.findById(id);
        if(categoryOld.isPresent()){
           Category categoryDb =  categoryOld.orElseThrow();  
           
           categoryDb.setDescription(category.getDescription());
           categoryDb.setStatus(category.getStatus());

           return Optional.of(categoryRepository.save(categoryDb));
        }

        return Optional.empty();
    }

    @Transactional(readOnly =  true)
    @Override
    public Optional<Category> delete(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        categoryOptional.ifPresent(categoryDb -> {
            categoryRepository.delete(categoryDb);
        }); 
        return categoryOptional;
    }

}