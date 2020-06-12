package com.management.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "collaborater_task")
public class CollaboraterTask implements Serializable{

    private static final long serialVersionUID = 1L;

    // Properties ---------------------------------->
    @EmbeddedId
    private CollaboraterTaskPK id;

    @Column(name = "working_hours", nullable = false)
    private Integer workingHours;

    // Relationships ------------------------------->
    // CollaboraterTask => Collaborater
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("collaborater_id")
    @JoinColumn(name = "collaborater_id")
    @JsonIgnoreProperties({"tasks", "hibernateLazyInitializer"})
    private User collaborater;

    // CollaboraterTask => Task
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("task_id")
    @JoinColumn(name = "task_id")
    @JsonIgnoreProperties({"project", "competences", "collaboraters", "hibernateLazyInitializer"})
    private Task task;

    // Constructors, getters and setters  ------------>
    public CollaboraterTask() {}

    public CollaboraterTaskPK getId() {
        return id;
    }

    public void setId(CollaboraterTaskPK id) {
        this.id = id;
    }

    public Integer getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Integer workingHours) {
        this.workingHours = workingHours;
    }

    public User getCollaborater() {
        return collaborater;
    }

    public void setCollaborater(User collaborater) {
        this.collaborater = collaborater;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}