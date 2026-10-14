package com.example.ecommerce.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CartRequest {

    @NotNull(message = "Product ID is required")
    private Long productId;

    @Positive(message = "Quantity must be greater ")
    private Integer quantity;

    public CartRequest(){

    }
    public Long getProductId(){
        return productId;
    }
    public void setProductId(Long productId){
        this.productId=productId;
    }
    public Integer getQuantity(){
        return quantity;
    }
    public void setQuantity(Integer quantity){
        this.quantity=quantity;
    }
}
