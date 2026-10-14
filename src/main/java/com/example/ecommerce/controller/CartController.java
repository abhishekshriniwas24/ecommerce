package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartRequest;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.service.CartService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public CartItem addToCart(
            @Valid @RequestBody CartRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        return cartService.addToCart(request, email);
    }
}
