package com.example.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.parameters.P;

public class RegisterRequest {

    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank
    @Email(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;

    public RegisterRequest() {

    }

    public  String getName() {
        return name;
    }
    public  void setName(String name){
        this.name=name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(){
        this.password=password;
    }
}
