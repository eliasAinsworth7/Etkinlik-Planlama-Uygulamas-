package com.alpr.project.service;
import org.springframework.stereotype.Service;

import com.alpr.project.dto.RegisterRequest;
import com.alpr.project.enums.Role;
import com.alpr.project.repository.UserRepository;
import com.alpr.project.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;

    public String register(RegisterRequest request){
        User user = User.builder()
        .fullName(request.getFullName())
        .email(request.getEmail())
        .password(request.getPassword())
        .role(Role.USER)
        .build();

        userRepository.save(user);

        return "Kullanıcı oluşturuldu";
    }
}
