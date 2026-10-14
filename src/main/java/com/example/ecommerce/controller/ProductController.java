package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductRequest;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;
    ProductController(ProductService productService){
        this.productService=productService;
    }
    @PostMapping
    public Product createProduct(@Valid @RequestBody ProductRequest request){
        return productService.createProduct(request);
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @GetMapping("/search")
    public Page<Product> searchProducts(
            @RequestParam String name,
            Pageable pageable
    ){
        return productService.searchProducts(name,pageable);
    }
}
