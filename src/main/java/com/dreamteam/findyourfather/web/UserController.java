package com.dreamteam.findyourfather.web;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.dreamteam.findyourfather.dao.UserRepository;
import com.dreamteam.findyourfather.entities.User;

@RestController
@RequestMapping("/Users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserRepository UserRepository;

    public UserController(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return (List<User>) UserRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return UserRepository.findById(id).orElse(null);
    }

    @PostMapping("/add")
    public void addUser(@RequestBody User User) {
        UserRepository.save(User);
    }

    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User User = UserRepository.findById(id).orElse(null);
        if (User != null) {
            User.setEmail(updatedUser.getEmail());
            User.setMdp(updatedUser.getMdp());
            User.setVisibilityLevel(updatedUser.getVisibilityLevel());
            UserRepository.save(User);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        UserRepository.deleteById(id);
    }
}
