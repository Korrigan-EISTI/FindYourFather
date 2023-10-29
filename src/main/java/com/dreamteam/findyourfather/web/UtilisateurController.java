package com.dreamteam.findyourfather.web;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.dreamteam.findyourfather.dao.UtilisateurRepository;
import com.dreamteam.findyourfather.entities.Utilisateur;

@RestController
@RequestMapping("/Users")
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/all")
    public List<Utilisateur> getAllUsers() {
        return (List<Utilisateur>) utilisateurRepository.findAll();
    }

    @GetMapping("/{id}")
    public Utilisateur getUserById(@PathVariable Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    @PostMapping("/add")
    public void addUser(@RequestBody Utilisateur User) {
        utilisateurRepository.save(User);
    }

    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody Utilisateur updatedUser) {
        Utilisateur User = utilisateurRepository.findById(id).orElse(null);
        if (User != null) {
            User.setEmail(updatedUser.getEmail());
            User.setMdp(updatedUser.getMdp());
            User.setVisibilityLevel(updatedUser.getVisibilityLevel());
            utilisateurRepository.save(User);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        utilisateurRepository.deleteById(id);
    }
}
