package com.management.backend.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.management.backend.model.Competence;
import com.management.backend.repository.CompetenceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CompetenceController {
    @Autowired
    private CompetenceRepository competenceRepository;

    // GET Resquests -------------------------------
    @GetMapping("/competences")
    public Page<Competence> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "rows", defaultValue = "5") Integer rows) {
        return this.competenceRepository.findAll(PageRequest.of(page, rows));
    }

    @GetMapping("/competences/{id}")
    public Competence get(@PathVariable Integer id) {
        return competenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find competence " + id));
    }

    @GetMapping("/competences/search")
    public List<Competence> search(@RequestParam(name = "keyword") String keyword) {
        return competenceRepository.searchByName(keyword);
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
        this.competenceRepository.deleteById(id);
    }

}