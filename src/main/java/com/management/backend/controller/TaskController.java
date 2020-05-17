package com.management.backend.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import com.management.backend.model.Task;
import com.management.backend.repository.TaskRepository;

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
public class TaskController {

    @Autowired
    TaskRepository taskRepository;
    
    // GET Resquests -------------------------------
    @GetMapping("/tasks")
    public List<Task> getAll() {
        return this.taskRepository.findAll();
    }

    @GetMapping("/tasks/{id}")
    public Task get(@PathVariable Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find Task " + id));
    }

    // POST Resquest -------------------------------
    @PostMapping("/tasks")
    public Task add(@Valid @RequestBody Task newTask) {
        return this.taskRepository.save(newTask);
    }

    // PUT Resquest -------------------------------
    @PutMapping("/tasks/{id}")
    public Task update(@Valid @RequestBody Task editTask, @PathVariable Integer id) {

        return this.taskRepository.findById(id).map(currentTask -> {
            currentTask.setName(editTask.getName());
            currentTask.setStartDate(editTask.getStartDate());
            currentTask.setEndDate(editTask.getEndDate());
            currentTask.setProject(editTask.getProject() == null ? currentTask.getProject() : editTask.getProject());

            return this.taskRepository.save(currentTask);
        }).orElseThrow(() -> new EntityNotFoundException("Could not find Task " + id));
    }

    // DELETE Resquest -------------------------------
    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable Integer id){
        Task currentTask = this.taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find task " + id));

        if (currentTask.getProject() != null && currentTask.getCollaboraters().isEmpty() && currentTask.getCompetences().isEmpty())
            this.taskRepository.deleteById(id);
        else
            throw new ConstraintViolationException("The task: '" + currentTask.getName()
                    + "' is associated with project or collaborators or competences, try to delete them first", null);   
    }
    
}