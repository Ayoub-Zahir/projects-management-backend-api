package com.management.backend.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import com.management.backend.model.Collaborater;
import com.management.backend.repository.CollaboraterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
public class CollaboraterController {
    @Autowired
    private CollaboraterRepository collaboraterRepository;

    // GET Resquests -------------------------------
    @GetMapping("/collaboraters")
    public List<Collaborater> getAll() {
        return this.collaboraterRepository.findAll();
    }

    @GetMapping("/collaboraters/{id}")
    public Collaborater get(@PathVariable Integer id) {
        return collaboraterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find collaborater " + id));
    }

    // POST Resquest -------------------------------
    @PostMapping("/collaboraters")
    public Collaborater add(@Valid @RequestBody Collaborater newCollaborater) {
        return this.collaboraterRepository.save(newCollaborater);
    }

    // PUT Resquest -------------------------------
    @PutMapping("/collaboraters/{id}")
    public Collaborater update(@Valid @RequestBody Collaborater editCollaborater, @PathVariable Integer id) {
        return this.collaboraterRepository.findById(id).map(currentCollaborater -> {
            currentCollaborater.setFirstName(editCollaborater.getFirstName());
            currentCollaborater.setLastName(editCollaborater.getLastName());
            
            return this.collaboraterRepository.save(currentCollaborater);
        }).orElseThrow(() -> new EntityNotFoundException("Could not find collaborater " + id));
    }

    // DELETE Resquest -------------------------------
    @DeleteMapping("/collaboraters/{id}")
    public void delete(@PathVariable Integer id) {
        this.collaboraterRepository.deleteById(id);

        Collaborater currentCollaborater = this.collaboraterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find competence " + id));

        if (currentCollaborater.getTasks().isEmpty() && currentCollaborater.getCompetences().isEmpty())
            this.collaboraterRepository.deleteById(id);
        else
            throw new ConstraintViolationException("The collaborater: '" + currentCollaborater.getFirstName()
                    + "' is associated with tasks or competences, try to delete them first", null);
    }
}