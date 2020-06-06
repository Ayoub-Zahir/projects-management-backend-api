package com.management.backend.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import com.management.backend.model.Project;
import com.management.backend.model.Task;
import com.management.backend.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/projects/{id}/tasks")
    public Page<Task> getTasks(
        @PathVariable Integer id, 
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "rows", defaultValue = "5") Integer rows
    ) {
        return projectRepository.findById(id).map(currentProject -> {
            // Page<Task> taskPage = new PageImpl<Task>(currentProject.getTasks(), PageRequest.of(page, 1), currentProject.getTasks().size());

            // return taskPage;
            PageRequest pageable = PageRequest.of(page, rows);
            
            int start = (int) pageable.getOffset();
            int end = (start + pageable.getPageSize()) > currentProject.getTasks().size() ? currentProject.getTasks().size() : (start + pageable.getPageSize());

            Page<Task> taskPage = new PageImpl<Task>(currentProject.getTasks().subList(start, end), pageable, currentProject.getTasks().size());
            
            return taskPage;
        }).orElseThrow(() -> new EntityNotFoundException("Could not find project " + id));
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
            throw new ConstraintViolationException("The project: '" + currentProject.getName()
                    + "' has tasks, try to delete the tasks associated with the project first", null);
    }

}