package pl.javowe.swirki.zzpjapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.javowe.swirki.zzpjapp.exception.UserNotFoundException;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {
    private UserRepository repository;

    @Autowired
    public AdminController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/admins")
    public List<User> getAllAdmins() {
        List<User> admins = new ArrayList<>();
        for (User user : repository.findAll()) {
            if(user.isAdmin() == true) {
                admins.add(user);
            }
        }
        return admins;
    }

    @GetMapping("/admin/{id}")
    public User getAdmin(@PathVariable Long id) throws UserNotFoundException {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/setAdmin/{id}")
    public User setAdmin(@PathVariable Long id) throws UserNotFoundException {
        if(repository.findById(id).isPresent()) {
            repository.findById(id).get().setAdmin(true);
            repository.save(repository.findById(id).get());
            return repository.getOne(id);
        }
        else throw new UserNotFoundException(id);
    }

    @PutMapping("/removeAdmin/{id}")
    public User removeAdmin(@PathVariable Long id) throws UserNotFoundException {
        if(repository.findById(id).isPresent()) {
            repository.findById(id).get().setAdmin(false);
            repository.save(repository.findById(id).get());
            return repository.getOne(id);
        }
        else throw new UserNotFoundException(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) throws UserNotFoundException {
        if(repository.findById(id).isPresent()) {
            repository.delete(repository.getOne(id));
        }
        else throw new UserNotFoundException(id);
    }
}
