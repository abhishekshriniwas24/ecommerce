package com.example.ecommerce.controller;

import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.dto.LoginResponse;
import com.example.ecommerce.dto.RegisterRequest;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody RegisterRequest request){
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid@RequestBody LoginRequest request){
        return
                userService.loginUser(request);
    }
    @GetMapping("/profile")
    public String profile(){
        return "Authenticated user";
    }
}
