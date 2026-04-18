package com.alpr.project.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpr.project.entity.Event;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByUserId(Long userId);

    Optional<Event> findByIdAndUserId(Long id, Long userId);
}
