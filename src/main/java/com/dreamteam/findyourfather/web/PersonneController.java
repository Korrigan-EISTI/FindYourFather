package com.dreamteam.findyourfather.web;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.dreamteam.findyourfather.dao.PersonneRepository;
import com.dreamteam.findyourfather.entities.Personne;

@RestController
@RequestMapping("/personnes")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonneController {

    private final PersonneRepository personneRepository;

    public PersonneController(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    @GetMapping("/all")
    public List<Personne> getAllPersonnes() {
        return (List<Personne>) personneRepository.findAll();
    }

    @GetMapping("/{id}")
    public Personne getPersonneById(@PathVariable Long id) {
        return personneRepository.findById(id).orElse(null);
    }

    @PostMapping("/add")
    public void addPersonne(@RequestBody Personne personne) {
        personneRepository.save(personne);
    }

    @PutMapping("/update/{id}")
    public void updatePersonne(@PathVariable Long id, @RequestBody Personne updatedPersonne) {
        Personne personne = personneRepository.findById(id).orElse(null);
        if (personne != null) {
            personne.setNom(updatedPersonne.getNom());
            personne.setPrenom(updatedPersonne.getPrenom());
            personneRepository.save(personne);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deletePersonne(@PathVariable Long id) {
        personneRepository.deleteById(id);
    }
}
