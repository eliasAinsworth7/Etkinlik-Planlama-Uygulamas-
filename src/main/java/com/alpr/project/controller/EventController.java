package com.alpr.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.alpr.project.dto.EventCreateRequest;
import com.alpr.project.dto.EventResponse;
import com.alpr.project.dto.EventUpdateRequest;
import com.alpr.project.service.EventService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    
    private final EventService eventService;

    @PostMapping
    public EventResponse createEvent(@RequestBody EventCreateRequest request, Authentication authentication) {
        String userEmail = authentication.getName();
        return eventService.CreateEvent(request, userEmail);
    }

    @GetMapping
    public List<EventResponse> getMyEvents(Authentication authentication) {
        String userEmail = authentication.getName();
        return eventService.getMyEvents(userEmail);
    }

    @GetMapping("/{id}")
    public EventResponse getMyEventById(@PathVariable Long id, Authentication authentication) {
        String userEmail = authentication.getName();
        return eventService.getMyEventById(id, userEmail);
    }

    @PostMapping("/{id}")
    public EventResponse updateMyEvent(@PathVariable Long id, @RequestBody EventUpdateRequest request, Authentication authentication) {
        String userEmail = authentication.getName();
        return eventService.updateMyEvent(id, request, userEmail);
    }

    @DeleteMapping("/{id}")
    public String deleteMyEvent(@PathVariable Long id, Authentication authentication){
        String userEmail = authentication.getName();
        return eventService.deleteMyEvent(id, userEmail);
    }
    
    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<EventResponse> getAllEvents() {
        return eventService.getAllEvents();
    }
    
    
    
}
