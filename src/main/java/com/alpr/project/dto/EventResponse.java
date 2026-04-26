package com.alpr.project.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.alpr.project.enums.EventCategory;
import com.alpr.project.enums.EventStatus;
import com.alpr.project.enums.Priority;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventResponse {
    private Long id;
    private String title;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;
    private String location;
    private EventCategory category;
    private EventStatus status;
    private Priority priority;
    private String userEmail;
}
