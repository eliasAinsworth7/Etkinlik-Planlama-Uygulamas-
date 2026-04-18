package com.alpr.project.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.alpr.project.enums.EventCategory;
import com.alpr.project.enums.EventStatus;
import com.alpr.project.enums.Priority;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EventUpdateRequest {
    @NotBlank(message = "Başlık boş olamaz")
    private String title;

    private String description;

    @NotNull(message = "Etkinlik tarihi boş olamaz")
    private LocalDate eventDate;

    @NotNull(message = "Başlama tarihi boş olamaz")
    private LocalTime startTime;

    @NotNull(message = "Bitiş tarihi boş olamaz")
    private LocalTime endTime;

    private String location;

    @NotNull(message = "Kategori boş olamaz")
    private EventCategory category;

    @NotNull(message = "Durum boş olamaz")
    private EventStatus status;

    @NotNull(message = "Öncelik boş olamaz")
    private Priority priority;
}
