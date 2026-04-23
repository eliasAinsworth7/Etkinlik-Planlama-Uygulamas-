package com.alpr.project.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


import com.alpr.project.enums.EventCategory;
import com.alpr.project.enums.EventStatus;
import com.alpr.project.enums.Priority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    
    private String destruction;

    @Column(nullable = false)
    private LocalDate eventDate;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    private String location;

    @Enumerated(EnumType.STRING)
    private EventCategory eventCategory;

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable =false)
    private User user;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @PrePersist
    protected void onCreate(){
        this.createAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updateAt = LocalDateTime.now();
    }
}
