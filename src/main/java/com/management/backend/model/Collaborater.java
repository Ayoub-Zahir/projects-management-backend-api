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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "collaboraters")
public class Collaborater implements Serializable{

    private static final long serialVersionUID = 1L;

    // Properties ---------------------------------->
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(name = "photo_url")
    private String photoURL;

    // Relationships ------------------------------->
    // Collaborater has many Competences
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "collaborater_competence", 
        joinColumns = @JoinColumn(name = "collaborater_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "competence_id", 
        referencedColumnName = "id")
    )
    @JsonIgnoreProperties({"tasks", "collaboraters"})
    private Set<Competence> competences;

    // Collaborater belong to many tasks
    @OneToMany(mappedBy = "collaborater")
    @JsonIgnoreProperties({"id","collaborater"})
    private Set<CollaboraterTask> tasks;


    // Constructors, getters and setters ------------>
    public Collaborater() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public Set<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(Set<Competence> competences) {
        this.competences = competences;
    }

    public Set<CollaboraterTask> getTasks() {
        return tasks;
    }

    public void setTasks(Set<CollaboraterTask> tasks) {
        this.tasks = tasks;
    }
    
}