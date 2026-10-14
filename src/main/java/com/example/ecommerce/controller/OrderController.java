package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderRequest;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }


    @GetMapping
    public java.util.List<Order> getMyOrders(
            Authentication authentication
    ){
        String email = authentication.getName();
        return orderService.getOrdersByUser(email);
    }
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id,Authentication authentication){

        String email =
                authentication.getName();
        return
                orderService.getOrderById(id,email);
    }

    @PostMapping
    public Order placeOrder(@Valid@RequestBody OrderRequest request,
                            Authentication authentication){
        String email =
                authentication.getName();

        return orderService.placeOrder(request,email);
    }

}
