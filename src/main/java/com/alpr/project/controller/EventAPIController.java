package com.alpr.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpr.project.dto.ApiResponse;
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

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/events")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class EventAPIController {
    
    private final EventService eventService;

    @PostMapping
    public ApiResponse<EventResponse> createEvent(@Valid @RequestBody EventCreateRequest request, Authentication authentication) {
        String userEmail = authentication.getName();
        EventResponse event = eventService.CreateEvent(request, userEmail);
        return new ApiResponse<EventResponse>(true, event, "Etkinlik oluşturuldu");
    }

    @GetMapping
    public ApiResponse<List<EventResponse>> getMyEvents(Authentication authentication) {
        String userEmail = authentication.getName();
        List<EventResponse> events = eventService.getMyEvents(userEmail);
        return new ApiResponse<List<EventResponse>>(true, events, "Etkinlikler listelendi");
    }

    @GetMapping("/{id}")
    public ApiResponse<EventResponse> getMyEventById(@PathVariable Long id, Authentication authentication) {
        String userEmail = authentication.getName();
        EventResponse event = eventService.getMyEventById(id, userEmail);
        return new ApiResponse<EventResponse>(true, event, "Etkinlik bulundu");
    }

    @PostMapping("/{id}")
    public ApiResponse<EventResponse> updateMyEvent(@Valid @PathVariable Long id, @RequestBody EventUpdateRequest request, Authentication authentication) {
        String userEmail = authentication.getName();
        EventResponse event = eventService.updateMyEvent(id, request, userEmail);
        return new ApiResponse<EventResponse>(true, event, "Etklinlik güncellendi");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteMyEvent(@PathVariable Long id, Authentication authentication){
        String userEmail = authentication.getName();
        String result = eventService.deleteMyEvent(id, userEmail);
        return new ApiResponse<String>(true, result, "Etkinlik silindi");
    }
    
    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<EventResponse>> getAllEvents() {
        List<EventResponse> events = eventService.getAllEvents();
        return new ApiResponse<List<EventResponse>>(true, events, "Admin için bütün etkinlikler listelendi");
    }
    
    
    
}
