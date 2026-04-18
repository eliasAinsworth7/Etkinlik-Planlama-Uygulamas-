package com.alpr.project.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.alpr.project.dto.AuthResponse;
import com.alpr.project.dto.LoginRequest;
import com.alpr.project.dto.RegisterRequest;
import com.alpr.project.enums.Role;
import com.alpr.project.exception.BadRequestException;
import com.alpr.project.exception.ResourceNotFoundException;
import com.alpr.project.repository.UserRepository;
import com.alpr.project.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService juJwtService;

    public String register(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new BadRequestException("Email zaten kullanılıyor");
        }
        User user = User.builder()
        .fullName(request.getFullName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();

        userRepository.save(user);

        return "Kullanıcı oluşturuldu";
    }

    public AuthResponse login(@RequestBody LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Kullanıcı Bulunamadı"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new BadRequestException("Şifre Yanlış");
        }
        String token = juJwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
