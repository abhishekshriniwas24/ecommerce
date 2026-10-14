package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CategoryRequest;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }


    @PostMapping
    public Category createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
          Category category = new Category();

          category.setName(categoryRequest.getName());

          category.setDescription(categoryRequest.getDescription());

          return
                  categoryService.saveCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return
                categoryService.getCategoryById(id);
    }
}
