package com.management.backend.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import com.management.backend.model.Competence;
import com.management.backend.repository.CompetenceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CompetenceController {
    @Autowired
    private CompetenceRepository competenceRepository;

    // GET Resquests -------------------------------
    @GetMapping("/competences")
    public List<Competence> getAll() {
        return this.competenceRepository.findAll();
    }

    @GetMapping("/competences/{id}")
    public Competence get(@PathVariable Integer id) {
        return competenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find competence " + id));
    }

    // POST Resquest -------------------------------
    @PostMapping("/competences")
    public Competence add(@Valid @RequestBody Competence newCompetence) {
        return this.competenceRepository.save(newCompetence);
    }

    // PUT Resquest -------------------------------
    @PutMapping("/competences/{id}")
    public Competence update(@Valid @RequestBody Competence editCompetence, @PathVariable Integer id) {

        return this.competenceRepository.findById(id).map(currentCompetence -> {
            currentCompetence.setName(editCompetence.getName());
            currentCompetence.setCollaboraters(editCompetence.getCollaboraters());
            currentCompetence.setTasks(editCompetence.getTasks());

            return this.competenceRepository.save(currentCompetence);
        }).orElseThrow(() -> new EntityNotFoundException("Could not find competence " + id));
    }

    // DELETE Resquest -------------------------------
    @DeleteMapping("/competences/{id}")
    public void delete(@PathVariable Integer id) {
        Competence currentCompetence = this.competenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find competence " + id));

        if (currentCompetence.getTasks().isEmpty() && currentCompetence.getCollaboraters().isEmpty())
            this.competenceRepository.deleteById(id);
        else
            throw new ConstraintViolationException("The competence: '" + currentCompetence.getName()
                    + "' is associated with tasks or collaborators, try to delete them first", null);
    }

}