package com.management.backend.repository;

import com.management.backend.model.CollaboraterTask;
import com.management.backend.model.CollaboraterTaskPK;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CollaboraterTaskRepository extends JpaRepository<CollaboraterTask, CollaboraterTaskPK>{ }