package com.management.backend.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import com.management.backend.model.Project;
import com.management.backend.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    // GET Resquests -------------------------------
    @GetMapping("/projects")
    public List<Project> getAll() {
        return this.projectRepository.findAll();
    }

    @GetMapping("/projects/{id}")
    public Project get(@PathVariable Integer id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find project " + id));
    }

    // POST Resquest -------------------------------
    @PostMapping("/projects")
    public Project add(@Valid @RequestBody Project newProject) {
        return this.projectRepository.save(newProject);
    }

    // PUT Resquest -------------------------------
    @PutMapping("/projects/{id}")
    public Project update(@Valid @RequestBody Project editProject, @PathVariable Integer id) {

        return this.projectRepository.findById(id).map(currentProject -> {
            currentProject.setName(editProject.getName());
            currentProject.setStartDate(editProject.getStartDate());
            currentProject.setEndDate(editProject.getEndDate());
            currentProject.setHourlyVolume(editProject.getHourlyVolume());

            return this.projectRepository.save(currentProject);
        }).orElseThrow(() -> new EntityNotFoundException("Could not find project " + id));
    }

    // DELETE Resquest -------------------------------
    @DeleteMapping("/projects/{id}")
    public void delete(@PathVariable Integer id) {
        Project currentProject = this.projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find project " + id));

        if (currentProject.getTasks().isEmpty())
            this.projectRepository.deleteById(id);
        else
            throw new ConstraintViolationException("The project: '" + currentProject.getName() + "' has tasks, try to delete the tasks associated with the project first", null);
    }

}