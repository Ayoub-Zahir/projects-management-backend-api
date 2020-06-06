package com.management.backend.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.management.backend.model.Collaborater;
import com.management.backend.model.CollaboraterTask;
import com.management.backend.model.CollaboraterTaskPK;
import com.management.backend.repository.CollaboraterRepository;
import com.management.backend.repository.CollaboraterTaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
public class CollaboraterController {
    @Autowired
    private CollaboraterRepository collaboraterRepository;

    @Autowired
    private CollaboraterTaskRepository collaboraterTaskRepository;

    // GET Resquests -------------------------------
    @GetMapping("/collaboraters")
    public Page<Collaborater> getAll(
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "rows", defaultValue = "5") Integer rows
    ) {
        return this.collaboraterRepository.findAll(PageRequest.of(page, rows));
    }

    @GetMapping("/collaboraters/{id}")
    public Collaborater get(@PathVariable Integer id) {
        return collaboraterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find collaborater " + id));
    }

    @GetMapping("/collaboraters/search")
    public List<Collaborater> search(
        @RequestParam(name = "keyword") String keyword
    ) {
        return collaboraterRepository.searchByName(keyword);
    }

    // POST Resquest -------------------------------
    @PostMapping("/collaboraters")
    public Collaborater add(@Valid @RequestBody Collaborater newCollaborater) {
        
        Collaborater collab = this.collaboraterRepository.save(newCollaborater);

        if(!newCollaborater.getTasks().isEmpty()){
            for(CollaboraterTask collabTask : newCollaborater.getTasks()) {

                CollaboraterTask collaboraterTask = new CollaboraterTask();

                collaboraterTask.setId(new CollaboraterTaskPK(collab.getId(), collabTask.getTask().getId()));
                collaboraterTask.setWorkingHours(collabTask.getWorkingHours());
                collaboraterTask.setTask(collabTask.getTask());

                this.collaboraterTaskRepository.save(collaboraterTask);
            }
        }

        return collab;
    }

    // PUT Resquest -------------------------------
    @PutMapping("/collaboraters/{id}")
    public Collaborater update(@Valid @RequestBody Collaborater editCollaborater, @PathVariable Integer id) {
        return this.collaboraterRepository.findById(id).map(currentCollaborater -> {
            currentCollaborater.setFirstName(editCollaborater.getFirstName());
            currentCollaborater.setLastName(editCollaborater.getLastName());
            currentCollaborater.setPhotoURL(editCollaborater.getPhotoURL());
            currentCollaborater.setEmail(editCollaborater.getEmail());
            currentCollaborater.setCompetences(editCollaborater.getCompetences());

            return this.collaboraterRepository.save(currentCollaborater);
        }).orElseThrow(() -> new EntityNotFoundException("Could not find collaborater " + id));
    }

    // DELETE Resquest -------------------------------
    @DeleteMapping("/collaboraters/{id}")
    public void delete(@PathVariable Integer id) {
        Collaborater currentCollaborater = this.collaboraterRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find collaborater " + id));

        if(!currentCollaborater.getTasks().isEmpty()){
            for(CollaboraterTask collabTask : currentCollaborater.getTasks()) {
                this.collaboraterTaskRepository.deleteById(new CollaboraterTaskPK(currentCollaborater.getId(), collabTask.getTask().getId()));
            }
        }

        this.collaboraterRepository.deleteById(id);
    }
}