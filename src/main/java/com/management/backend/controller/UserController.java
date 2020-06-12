package com.management.backend.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.management.backend.model.CollaboraterTask;
import com.management.backend.model.CollaboraterTaskPK;
import com.management.backend.model.User;
import com.management.backend.repository.CollaboraterTaskRepository;
import com.management.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollaboraterTaskRepository collaboraterTaskRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // GET Requests -------------------------------
    @GetMapping("/api/users/collaboraters")
    public Page<User> getCollaboraters(
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "rows", defaultValue = "5") Integer rows
    ) {
        return this.userRepository.findByRole("ROLE_COLLABORATER", PageRequest.of(page, rows));
    }

    @GetMapping("/api/manager/users/collaboraters/search")
    public List<User> searchCollaboraters(
        @RequestParam(name = "keyword") String keyword
    ) {
        return this.userRepository.searchCollaboraterByName(keyword);
    }

    @GetMapping("/api/admin/users/managers")
    public Page<User> getManagers(
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "rows", defaultValue = "5") Integer rows
    ) {
        return this.userRepository.findByRole("ROLE_MANAGER", PageRequest.of(page, rows));
    }

    @GetMapping("/api/admin/users/{id}")
    public User getUser(@PathVariable Integer id) {
        return this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find collaborater " + id));
    }

    // POST Request ## Only Admins -------------------------------
    @PostMapping("/api/admin/users")
    public User add(@RequestBody User newUser) throws DataIntegrityViolationException{
        try {
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            User user = this.userRepository.save(newUser);
            return user;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Email already exist");
        }
    }

    // PUT Request ## Only Admins -------------------------------
    @PutMapping("/api/admin/users/{id}")
    public User update(@RequestBody User editUser, @PathVariable Integer id) {
        return this.userRepository.findById(id).map(currentUser -> {
            currentUser.setFirstName(editUser.getFirstName());
            currentUser.setLastName(editUser.getLastName());
            currentUser.setEmail(editUser.getEmail());
            currentUser.setPhotoURL(editUser.getPhotoURL());
            currentUser.setRole(editUser.getRole());
            currentUser.setActive(editUser.isActive());
            currentUser.setCompetences(editUser.getCompetences());
            
            try {
                return this.userRepository.save(currentUser);
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException("Email already exist");
            }

        }).orElseThrow(() -> new EntityNotFoundException("Could not find user " + id));
    }

    // DELETE Request ## Only Admins-------------------------------
    @DeleteMapping("/api/admin/users/{id}")
    public void delete(@PathVariable Integer id) {
        User currentUser = this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find collaborater " + id));

        if(!currentUser.getTasks().isEmpty()){
            for(CollaboraterTask collabTask : currentUser.getTasks()) {
                this.collaboraterTaskRepository.deleteById(new CollaboraterTaskPK(currentUser.getId(), collabTask.getTask().getId()));
            }
        }

        this.userRepository.deleteById(id);
    }
}