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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequiredArgsConstructor
public class AuthPageController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginPatch(Model model) {
        model.addAttribute("loginRequest",new LoginRequest());
        return "login";
    }
    

    @GetMapping("/register")
    public String regiterPage(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("registerRequest")RegisterRequest request, RedirectAttributes redirectAttributes,BindingResult result) {
        if(result.hasErrors()){
            return "redirect:/login?registered=false";
        }
        redirectAttributes.addAttribute("messageType","success");
        redirectAttributes.addAttribute("messageKey", "register.success");
        authService.register(request);
        return "redirect:/login?registered=true";
    }
    
    
    
    
}
