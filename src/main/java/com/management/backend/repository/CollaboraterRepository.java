package com.management.backend.repository;

import java.util.List;

import com.management.backend.model.Collaborater;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CollaboraterRepository extends JpaRepository<Collaborater, Integer>{ 

    @Query("SELECT c FROM Collaborater c WHERE c.firstName LIKE :keyword% OR c.lastName LIKE :keyword%")
    List<Collaborater> searchByName(@Param("keyword") String keyword);
}