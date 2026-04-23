package com.alpr.project.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.alpr.project.service.EventService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class EventPageController {
    
    private final EventService eventService;

    @GetMapping("/events")
    public String getMethodName(Model model, Authentication auth) {
        model.addAttribute("events",eventService.getMyEvents(auth.getName()));
        return "events";
    }
    
}
