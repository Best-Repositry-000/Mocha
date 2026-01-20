package com.example.mocha.service;

import com.example.mocha.Repository.UserRepository;
import com.example.mocha.dto.loginDto;
import com.example.mocha.dto.registerDto;
import com.example.mocha.enums.Role;
import com.example.mocha.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(registerDto user){
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setMatricNo(user.getMatricNo());
        newUser.setEmail(user.getEmail());
        String password = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(password);
        newUser.setRole(Role.STUDENT);
        userRepository.save(newUser);
    }

    public void login(loginDto user){
        User existingUser = userRepository.findByMatricNo(user.getMatricNo())
                .orElseThrow(()-> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())){
            throw new RuntimeException("Wrong password");
        }
    }
}
