package com.alpr.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.alpr.project.dto.LoginRequest;
import com.alpr.project.dto.RegisterRequest;
import com.alpr.project.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequiredArgsConstructor
public class AuthPageController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginPatch(Model model) {
        model.addAttribute("loginRequest",new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String postMethodName(@Valid @ModelAttribute("loginRequest") LoginRequest request, BindingResult result,Model model) {
        if(result.hasErrors()){
            model.addAttribute("loginRequest", request);
            return "login";
        }
        
        return "events";
    }
    

    @GetMapping("/register")
    public String regiterPage(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("registerRequest")RegisterRequest request, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:/login?registered=false";
        }
        authService.register(request);
        return "redirect:/login?registered=true";
    }
    
    
    
    
}
