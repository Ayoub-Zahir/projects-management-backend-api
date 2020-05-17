package com.management.backend.repository;

import com.management.backend.model.Competence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
    
}