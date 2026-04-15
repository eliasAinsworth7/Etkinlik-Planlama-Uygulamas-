package com.alpr.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpr.project.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
