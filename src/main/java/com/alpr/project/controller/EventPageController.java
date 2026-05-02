package com.alpr.project.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.alpr.project.dto.EventCreateRequest;
import com.alpr.project.dto.EventUpdateRequest;
import com.alpr.project.enums.EventCategory;
import com.alpr.project.enums.EventStatus;
import com.alpr.project.enums.Priority;
import com.alpr.project.service.EventService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




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
    @GetMapping("/edit/{id}")
    public String getMethodName(@PathVariable Long id, Model model, Authentication auth) {
        model.addAttribute("event", eventService.getMyEventById(id, auth.getName()));
        model.addAttribute("categories", EventCategory.values());
        model.addAttribute("statuses", EventStatus.values());
        model.addAttribute("priorities",Priority.values());
        return "edit-event";
    }
    @PostMapping("/add")
    public String addEvent(@Valid @ModelAttribute("event") EventCreateRequest request,
    BindingResult result, RedirectAttributes redirectAttributes,Authentication auth,Model model) {
        if(result.hasErrors()){
            model.addAttribute("event",request);
            model.addAttribute("categories", EventCategory.values());
            model.addAttribute("statuses", EventStatus.values());
            model.addAttribute("priorities",Priority.values());
            redirectAttributes.addFlashAttribute("messageType","danger");
            redirectAttributes.addFlashAttribute("messageKey","event.create.failure");
            return "add-event";
        }
        try{
            eventService.CreateEvent(request, auth.getName());
            redirectAttributes.addFlashAttribute("messageType","success");
            redirectAttributes.addFlashAttribute("messageKey","event.create.success");
            return "redirect:/events";
        }catch(Exception e){
            model.addAttribute("event",request);
            model.addAttribute("categories", EventCategory.values());
            model.addAttribute("statuses", EventStatus.values());
            model.addAttribute("priorities",Priority.values());
            model.addAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("messageType","danger");
            redirectAttributes.addFlashAttribute("messageKey","event.create.failure");
            return "add-event";
        }
        
    }
    
    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id, Authentication auth,RedirectAttributes redirectAttributes) {
        eventService.deleteMyEvent(id, auth.getName());
        redirectAttributes.addFlashAttribute("messageType","success");
        redirectAttributes.addFlashAttribute("messageKey","event.delete.success");
        return "redirect:/events";
    }
    @PostMapping("/update/{id}")
    public String postMethodName(@PathVariable Long id, @Valid @ModelAttribute EventUpdateRequest request, BindingResult result,Authentication auth,RedirectAttributes redirectAttributes, Model model) {
        if(result.hasErrors()){
            model.addAttribute("event", eventService.getMyEventById(id, auth.getName()));
            model.addAttribute("categories", EventCategory.values());
            model.addAttribute("statuses", EventStatus.values());
            model.addAttribute("priorities",Priority.values());
            redirectAttributes.addFlashAttribute("messageKey","event.update.failure");
            redirectAttributes.addFlashAttribute("messageType","danger");
            return "edit-event";
        }

        try{
            eventService.updateMyEvent(id, request, auth.getName());
            redirectAttributes.addFlashAttribute("messageKey","event.update.success");
            redirectAttributes.addFlashAttribute("messageType","success");
            return "redirect:/events";
        } catch(Exception e){
            model.addAttribute("event", eventService.getMyEventById(id, auth.getName()));
            model.addAttribute("categories", EventCategory.values());
            model.addAttribute("statuses", EventStatus.values());
            model.addAttribute("priorities",Priority.values());
            model.addAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("messageKey","event.update.failure");
            redirectAttributes.addFlashAttribute("messageType","danger");
            return "edit-event";
        }
        
    }
    
    
    
    
    
}
