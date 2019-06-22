package pl.javowe.swirki.zzpjapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.javowe.swirki.zzpjapp.exception.UserInvalidDataException;
import pl.javowe.swirki.zzpjapp.exception.UserNotFoundException;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;
import pl.javowe.swirki.zzpjapp.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {
    private UserRepository repository;
    private UserService userService;

    @Autowired
    public AdminController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/admins")
    public List<User> getAllAdmins() {
        List<User> admins = new ArrayList<>();
        for (User user : repository.findAll()) {
            if(user.isAdmin()) {
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

    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user) throws UserInvalidDataException {
        //return this.repository.save(user);
        return this.userService.save(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) throws UserNotFoundException {
        if(repository.findById(id).isPresent()) {
            userService.deleteUser(id);
        }
        else throw new UserNotFoundException(id);
    }
}
