package com.management.backend.repository;

import java.util.List;
import java.util.Optional;

import com.management.backend.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByEmail(String email);

    Page<User> findByRole(String role, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.role = 'ROLE_COLLABORATER' AND u.firstName LIKE :keyword% OR u.lastName LIKE :keyword%")
    List<User> searchCollaboraterByName(@Param("keyword") String keyword);
}
