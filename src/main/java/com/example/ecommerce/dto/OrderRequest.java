package com.example.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderRequest {

    @NotNull(message = "Product ID is required")
    private Long productId;

    @Positive(message = "Quantity must be greater than zero")
    private Integer quantity;

    public OrderRequest(){

    }
    public Long getProductId(){
        return productId;
    }
    public void setProductId(){
        this.productId=productId;
    }

    public Integer getQuantity(){
        return quantity;
    }
    public void setQuantity(Integer quantity){
        this.quantity=quantity;
    }
}
