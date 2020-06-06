package com.management.backend.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.management.backend.model.CollaboraterTask;
import com.management.backend.model.CollaboraterTaskPK;
import com.management.backend.model.Task;
import com.management.backend.repository.CollaboraterTaskRepository;
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
    private TaskRepository taskRepository;

    @Autowired
    private CollaboraterTaskRepository collaboraterTaskRepository;
    
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
        Task currentTask = this.taskRepository.save(newTask);

        if(!newTask.getCollaboraters().isEmpty()){
            for(CollaboraterTask collabTask : newTask.getCollaboraters()) {

                CollaboraterTask collaboraterTask = new CollaboraterTask();

                collaboraterTask.setId(new CollaboraterTaskPK(collabTask.getCollaborater().getId(), currentTask.getId()));
                collaboraterTask.setWorkingHours(collabTask.getWorkingHours());
                collaboraterTask.setCollaborater(collabTask.getCollaborater());

                this.collaboraterTaskRepository.save(collaboraterTask);
            }
        }

        return currentTask;
    }

    // PUT Resquest -------------------------------
    @PutMapping("/tasks/{id}")
    public Task update(@Valid @RequestBody Task editTask, @PathVariable Integer id) {

        return this.taskRepository.findById(id).map(currentTask -> {
            currentTask.setName(editTask.getName());
            currentTask.setHourlyVolume(editTask.getHourlyVolume());
            currentTask.setStartDate(editTask.getStartDate());
            currentTask.setEndDate(editTask.getEndDate());
            currentTask.setDescription(editTask.getDescription());
            currentTask.setIsComplete(editTask.getIsComplete());
            currentTask.setProject(editTask.getProject());
            currentTask.setCompetences(editTask.getCompetences());

            // Update task collaboraters
            if(editTask.getCollaboraters().isEmpty()){
                // Delete All
                for(CollaboraterTask collabTask : currentTask.getCollaboraters()) {
                    this.collaboraterTaskRepository.deleteById(new CollaboraterTaskPK(collabTask.getCollaborater().getId(), currentTask.getId()));
                }
            } 
            else {
                // Delete old
                 for(CollaboraterTask collabTask : currentTask.getCollaboraters()) {
                    this.collaboraterTaskRepository.deleteById(new CollaboraterTaskPK(collabTask.getCollaborater().getId(), currentTask.getId()));
                }

                // Insert new
                for(CollaboraterTask collabTask : editTask.getCollaboraters()) {
                    CollaboraterTask collaboraterTask = new CollaboraterTask();
    
                    collaboraterTask.setId(new CollaboraterTaskPK(collabTask.getCollaborater().getId(), currentTask.getId()));
                    collaboraterTask.setWorkingHours(collabTask.getWorkingHours());
                    collaboraterTask.setCollaborater(collabTask.getCollaborater());
    
                    this.collaboraterTaskRepository.save(collaboraterTask);
                }
            }

            return this.taskRepository.save(currentTask);
        }).orElseThrow(() -> new EntityNotFoundException("Could not find Task " + id));
    }

    // DELETE Resquest -------------------------------
    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable Integer id){
        Task currentTask = this.taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find task " + id));

        if(!currentTask.getCollaboraters().isEmpty()){
            for(CollaboraterTask collabTask : currentTask.getCollaboraters()) {
                this.collaboraterTaskRepository.deleteById(new CollaboraterTaskPK(collabTask.getCollaborater().getId(), currentTask.getId()));
            }
        }

        this.taskRepository.deleteById(id);
    }
    
}