package com.alpr.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alpr.project.dto.EventCreateRequest;
import com.alpr.project.dto.EventResponse;
import com.alpr.project.dto.EventUpdateRequest;
import com.alpr.project.entity.Event;
import com.alpr.project.entity.User;
import com.alpr.project.exception.BadRequestException;
import com.alpr.project.exception.ResourceNotFoundException;
import com.alpr.project.repository.EventRepository;
import com.alpr.project.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventResponse CreateEvent(EventCreateRequest request, String userEmail){
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("Kullanıcı Bulunamadı"));
        if(request.getEndTime().isBefore(request.getStartTime())){
            throw new BadRequestException("Bitiş saati başlangıç saatinden önce olamaz");
        }
        Event event = Event.builder()
            .title(request.getTitle())
            .destruction(request.getDescription())
            .eventDate(request.getEventDate())
            .startTime(request.getStartTime())
            .endTime(request.getEndTime())
            .location(request.getLocation())
            .eventCategory(request.getCategory())
            .eventStatus(request.getStatus())
            .priority(request.getPriority())
            .user(user)
            .build();

            Event savedEvent = eventRepository.save(event);

            return mapToResponse(savedEvent);

    }
    public List<EventResponse> getAllEvents(){
        return eventRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public List<EventResponse> getMyEvents(String userEmail){
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı Bulunamadı"));
        
        return eventRepository.findByUserId(user.getId())
            .stream()
            .map(this::mapToResponse)
            .toList();
    }

    public EventResponse getMyEventById(Long eventId, String userEmail){
        User user = userRepository.findByEmail(userEmail)
        .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı Bulunamadı"));

        Event event = eventRepository.findByIdAndUserId(eventId, user.getId())
        .orElseThrow(() -> new ResourceNotFoundException("Etkinlik Bulunamadı"));

        return mapToResponse(event);
    }

    public EventResponse updateMyEvent(Long eventId, EventUpdateRequest request, String userEmail){
        User user = userRepository.findByEmail(userEmail)
        .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı Bulunamadı"));
        if(request.getEndTime().isBefore(request.getStartTime())){
            throw new BadRequestException("Bitiş saati başlangıç saatinden önce olamaz");
        }
        Event event = eventRepository.findByIdAndUserId(eventId, user.getId())
        .orElseThrow(() -> new ResourceNotFoundException("Etkinlik Bulunamadı"));

        event.setTitle(request.getTitle());
        event.setDestruction(request.getDescription());
        event.setEventDate(request.getEventDate());
        event.setStartTime(request.getStartTime());
        event.setEndTime(request.getEndTime());
        event.setLocation(request.getLocation());
        event.setEventCategory(request.getCategory());
        event.setEventStatus(request.getStatus());
        event.setPriority(request.getPriority());

        Event updatedEvent = eventRepository.save(event);

        return mapToResponse(updatedEvent);
    }

    public String deleteMyEvent(Long eventId, String userEmail){
        User user = userRepository.findByEmail(userEmail)
        .orElseThrow(() -> new ResourceNotFoundException("Kullanıcı Bulunamadı"));

        Event event = eventRepository.findByIdAndUserId(eventId, user.getId())
        .orElseThrow(() -> new ResourceNotFoundException("Etkinlik Bulunamadı"));

        eventRepository.delete(event);
        return "Etkinlik Silindi";
    }

    private EventResponse mapToResponse(Event event){
        return EventResponse.builder()
        .id(event.getId())
        .title(event.getTitle())
        .description(event.getDestruction())
        .eventDate(event.getEventDate())
        .startTime(event.getStartTime())
        .endTime(event.getEndTime())
        .location(event.getLocation())
        .category(event.getEventCategory())
        .status(event.getEventStatus())
        .priority(event.getPriority())
        .userEmail(event.getUser().getEmail())
        .build();
    }
    
}
