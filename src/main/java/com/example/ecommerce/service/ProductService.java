package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductRequest;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.exception.CategoryNotFoundException;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    private ProductService(ProductRepository productRepository , CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;

    }
    public Product createProduct(ProductRequest request){
        Category category = categoryRepository.findById(request
                .getCategoryId())
                .orElseThrow(()->
                        new CategoryNotFoundException(
                                "Category not found with id: " +
                                        request.getCategoryId()
                        )
                );
        Product product = new Product();

        product.setName(request.getName());

        product.setDescription(request.getDescription());

        product.setPrice(request.getPrice());

        product.setStock(request.getStock());

        product.setCategory(category);

        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()->new
         RuntimeException("Product not Found" +id));
    }

    public Page<Product> searchProducts(
            String name,
            Pageable pageable
    ){
        return productRepository
                .findByNameContainingIgnoreCase(name,pageable);
    }
}
