package com.management.backend.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tasks")
public class Task implements Serializable{
    
    private static final long serialVersionUID = 1L;

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

    @Column(name = "is_complete",columnDefinition = "boolean default false")
    private Boolean isComplete;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate; 

    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;


    // Relationships ------------------------------->
    // Task belong to one Project
    @ManyToOne(fetch = FetchType.LAZY)      
    @JoinColumn(name = "project_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","tasks"})
    private Project project;

    // Task belong to many Competences
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "task_competence", 
        joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "competence_id", 
        referencedColumnName = "id")
    )
    @JsonIgnoreProperties({"tasks","collaboraters"})
    private Set<Competence> competences;

    // Task belong to many Collaboraters 
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
    @JsonIgnoreProperties({"id", "task"})
    private Set<CollaboraterTask> collaboraters;
    
    
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

    public Boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    public Set<CollaboraterTask> getCollaboraters() {
        return collaboraters;
    }

    public void setCollaboraters(Set<CollaboraterTask> collaboraters) {
        this.collaboraters = collaboraters;
    }

}