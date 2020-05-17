package com.management.backend.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tasks")
public class Task {

    // Properties ---------------------------------->
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(name = "hourly_volume", nullable = false)
    private Integer hourlyVolume;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate; 

    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;


    // Relationships ------------------------------->
    // Task belong to one Project
    @ManyToOne(fetch = FetchType.LAZY)      
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","tasks"})
    private Project project;

    // Task belong to many Collaboraters
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
    @JsonIgnoreProperties({"id", "task"})
    private Set<CollaboraterTask> collaboraters;

    // Task belong to many Competences
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tasks")
    @JsonIgnoreProperties({"tasks","collaboraters"})
    private Set<Competence> competences;
    
    
    // Constructors, getters and setters  ------------>
    public Task() {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHourlyVolume() {
        return hourlyVolume;
    }

    public void setHourlyVolume(Integer hourlyVolume) {
        this.hourlyVolume = hourlyVolume;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(Set<Competence> competences) {
        this.competences = competences;
    }

    public Set<CollaboraterTask> getCollaboraters() {
        return collaboraters;
    }

    public void setCollaboraters(Set<CollaboraterTask> collaboraters) {
        this.collaboraters = collaboraters;
    }

}