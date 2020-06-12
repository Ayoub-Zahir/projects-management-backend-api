package com.management.backend.repository;

import java.util.List;

import com.management.backend.model.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    
    @Query("SELECT DISTINCT p FROM Project AS p, Task AS t WHERE p.id = t.project.id AND t.id IN (SELECT ct.task.id FROM CollaboraterTask as ct WHERE ct.collaborater.id = :collaboraterId)")
    List<Project> findCollaboraterProjects(@Param("collaboraterId") Integer collaboraterId);
}