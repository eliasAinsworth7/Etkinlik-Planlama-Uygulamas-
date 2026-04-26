package com.alpr.project.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.alpr.project.dto.EventCreateRequest;
import com.alpr.project.enums.EventCategory;
import com.alpr.project.enums.EventStatus;
import com.alpr.project.enums.Priority;
import com.alpr.project.service.EventService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventPageController {
    
    private final EventService eventService;

    @GetMapping()
    public String getEvents(Model model, Authentication auth) {
        model.addAttribute("events",eventService.getMyEvents(auth.getName()));
        return "events";
    }
    @GetMapping("/add")
    public String getAddEvent(Model model) {
        model.addAttribute("event", new EventCreateRequest());
        model.addAttribute("categories", EventCategory.values());
        model.addAttribute("statuses", EventStatus.values());
        model.addAttribute("priorities",Priority.values());
        return "add-event";
    }
    @PostMapping("/add")
    public String addEvent(@Valid @ModelAttribute("event") EventCreateRequest request, Authentication auth,
    BindingResult result) {
        if(result.hasErrors()){
            return "redirect:/events/add?adding=false";
        }
        eventService.CreateEvent(request, auth.getName());
        return "events";
    }
    
    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id, Authentication auth) {
        eventService.deleteMyEvent(id, auth.getName());
        return "events";
    }
    
    
    
}
