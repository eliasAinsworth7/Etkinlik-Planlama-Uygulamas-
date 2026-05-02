package com.alpr.project.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.alpr.project.enums.EventCategory;
import com.alpr.project.enums.EventStatus;
import com.alpr.project.enums.Priority;

import lombok.Data;
import jakarta.validation.constraints.*;
@Data
public class EventCreateRequest {
    @NotBlank(message = "Başlık boş olamaz")
    private String title;
    
    private String description;

    @NotNull(message = "Etkinlik tarihi boş olamaz")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate eventDate;

    @NotNull(message = "Başlangıç saati boş olamaz")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @NotNull(message = "Bitiş saati boş olamaz")
    @DateTimeFormat(pattern ="HH:mm")
    private LocalTime endTime;


    private String location;

    @NotNull(message = "Kategori boş olamaz")
    private EventCategory category;

    @NotNull(message = "Durum boş olamaz")
    private EventStatus status;

    @NotNull(message = "Öncelik boş olamaz")
    private Priority priority;
}
