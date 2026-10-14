package com.example.ecommerce.service;

import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.dto.LoginResponse;
import com.example.ecommerce.dto.RegisterRequest;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private  final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtUtil jwtUtil){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtUtil=jwtUtil;
    }

    public User registerUser(RegisterRequest request){

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new
                    RuntimeException("Email is already registered");
        }
            User user = new User();

            user.setName(request.getName());
            user.setEmail(request.getEmail());

            String encodedPassword =
                    passwordEncoder.encode(request.getPassword());

            user.setPassword(encodedPassword);
            user.setRole("CUSTOMER");
            return userRepository.save(user);
        }
        public LoginResponse loginUser(LoginRequest request){
        User user =
                userRepository.findByEmail(request.getEmail())
                        .orElseThrow(()->
                                new
                                        RuntimeException("Invalid Email or Password"));
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )){
            throw new
                    RuntimeException("Invalid email or password");
        }
        String token =
                jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token);
        }
    }

