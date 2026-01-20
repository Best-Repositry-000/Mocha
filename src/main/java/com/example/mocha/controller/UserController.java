package com.example.mocha.controller;

import com.example.mocha.Repository.UserRepository;
import com.example.mocha.dto.loginDto;
import com.example.mocha.dto.registerDto;
import com.example.mocha.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody registerDto newUser){
        userService.registerUser(newUser);
        return "User registered successfully";
    }

    @GetMapping("/login")
    public void login(@RequestBody loginDto user){
        userService.login(user);
    }
}
