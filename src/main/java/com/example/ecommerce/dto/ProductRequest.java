package com.example.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class ProductRequest {

    @NotBlank(message = "Name is reuired")
    private String name;

    @NotBlank(message = "Description is required ")
    private String description;

    @Positive(message = "Price should be positive")
    private BigDecimal price;

    @PositiveOrZero
    private Integer stock;

    @NotNull(message = "Category Id Should not be null")
    private Long categoryId;

    public ProductRequest() {

    }

    public String getName(){
        return name;
    }
    public void setName(){
        this.name=name;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(){
        this.description=description;
    }
    public BigDecimal getPrice(){
        return price;
    }
    public void setPrice(BigDecimal price){
        this.price=price;
    }
    public Integer getStock(){
        return stock;
    }
    public void setStock(Integer stock){
        this.stock=stock;
    }
    public Long getCategoryId(){
        return categoryId;
    }
    public void setCategoryId(Long categoryId){
        this.categoryId=categoryId;
    }
}


