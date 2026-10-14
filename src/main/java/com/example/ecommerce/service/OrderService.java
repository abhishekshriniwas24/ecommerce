package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderRequest;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.OrderItemRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository , OrderItemRepository orderItemRepository , ProductRepository productRepository,UserRepository userRepository){
        this.orderRepository=orderRepository;
        this.orderItemRepository=orderItemRepository;
        this.productRepository=productRepository;
        this.userRepository=userRepository;
    }

    @Transactional
    public Order placeOrder(OrderRequest request,String email){
        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(()->
                                new RuntimeException("User not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(()->
                        new RuntimeException("Product not found"));

        if (product.getStock() <
                request.getQuantity()){
            throw new
                    RuntimeException("Insufficient stock");
        }
        BigDecimal totalAmount =
                product.getPrice()
                        .multiply(BigDecimal.valueOf(request.getQuantity()));

        Order order = new Order();

        order.setTotalAmount(totalAmount);
        order.setStatus("PLACED");

        order.setOrderDate(LocalDateTime.now());

        order.setUser(user);

        Order savedOrder = orderRepository.save(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(request.getQuantity());
        orderItem.setPrice(product.getPrice());
        orderItem.setOrder(savedOrder);
        orderItem.setProduct(product);

        orderItemRepository.save(orderItem);
        product.setStock(
                product.getStock() -
                        request.getQuantity()
        );

        productRepository.save(product);

        return savedOrder;
    }
  public java.util.List<Order> getOrdersByUser(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new
                        RuntimeException("User not found"));

        return
                orderRepository.findByUserId(user.getId());
  }
  public Order getOrderById(Long orderId , String email){

        User user= userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found"));

        Order order =
                orderRepository.findById(orderId)
                        .orElseThrow(()->new RuntimeException("Order not found"));

        if (!
        order.getUser().getId().equals(user.getId())){
            throw new RuntimeException("You are not authorized to view this order");
        }

        return
               order;
  }
}
