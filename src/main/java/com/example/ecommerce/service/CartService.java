package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartRequest;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository , CartItemRepository cartItemRepository , ProductRepository productRepository , UserRepository userRepository){
        this.cartRepository=cartRepository;
        this.cartItemRepository=cartItemRepository;
        this.productRepository=productRepository;
        this.userRepository=userRepository;
    }

    public CartItem addToCart(CartRequest request , String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->
                        new
                                RuntimeException("User Not Found"));

        Cart cart = cartRepository.save(new Cart(user));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(()->
                        new
                                RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();

        cartItem.setCart(cart);
        cartItem.setProduct(product);

        return
                cartItemRepository.save(cartItem);
    }
}
