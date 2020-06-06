package com.management.backend.repository;

import java.util.List;

import com.management.backend.model.Competence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompetenceRepository extends JpaRepository<Competence, Integer> {

    @Query("SELECT c FROM Competence c WHERE c.name LIKE :keyword%")
    List<Competence> searchByName(@Param("keyword") String keyword);
}