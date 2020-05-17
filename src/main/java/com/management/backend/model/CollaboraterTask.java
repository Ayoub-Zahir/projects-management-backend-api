package com.management.backend.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "collaborater_task")
public class CollaboraterTask {

    // Properties ---------------------------------->
    @EmbeddedId
    private CollaboraterTaskPK id;

    @Column(name = "working_hours", nullable = false)
    private Integer workingHours;

    // Relationships ------------------------------->
    // CollaboraterTask => Collaborater
    @ManyToOne
    @MapsId("collaborater_id")
    @JoinColumn(name = "collaborater_id")
    @JsonIgnoreProperties({"competences", "tasks"})
    private Collaborater collaborater;

    // CollaboraterTask => Task
    @ManyToOne
    @MapsId("task_id")
    @JoinColumn(name = "task_id")
    @JsonIgnoreProperties({"project", "competences", "collaboraters"})
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

    public Collaborater getCollaborater() {
        return collaborater;
    }

    public void setCollaborater(Collaborater collaborater) {
        this.collaborater = collaborater;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}