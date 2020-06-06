package com.management.backend.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "competences")
public class Competence implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // Properties ---------------------------------->
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String name;

    // Relationships ------------------------------->
    // Competence belong to many Tasks
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "task_competence", 
        joinColumns = @JoinColumn(name = "competence_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "task_id", 
        referencedColumnName = "id")
    )
    @JsonIgnoreProperties({"project","collaboraters", "competences"})
    private Set<Task> tasks;
    
    // Competence belong to many Collaboraters
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "collaborater_competence", 
        joinColumns = @JoinColumn(name = "competence_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "collaborater_id", 
        referencedColumnName = "id")
    )
    @JsonIgnoreProperties({"competences","tasks"})
    private Set<Collaborater> collaboraters;

    // Constructors, getters and setters  ------------>
    public Competence() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Collaborater> getCollaboraters() {
        return collaboraters;
    }

    public void setCollaboraters(Set<Collaborater> collaboraters) {
        this.collaboraters = collaboraters;
    }

}