package com.example.ecommerce.service;


import com.example.ecommerce.entity.Category;
import com.example.ecommerce.exception.CategoryNotFoundException;
import com.example.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);

    }
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        return
                categoryRepository.findById(id)
                        .orElseThrow(()->
                                new
                                CategoryNotFoundException(
                                        "Category not found with id: " + id
                                )
                        );
    }

}
