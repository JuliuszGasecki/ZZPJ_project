package pl.javowe.swirki.zzpjapp.controller;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.javowe.swirki.zzpjapp.exception.UserNotFoundException;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;

import java.util.List;


@RestController
public class UserController {

    private UserRepository repository;
    @Autowired
    public UserController(UserRepository repository){
        this.repository = repository;
    }
    //Root <- access to all records from repository

    @GetMapping("/users") //<- method for http Get request
    List<User> getAllUsers(){
        return this.repository.findAll();
    }

    @PostMapping("/users") //<- method for http Post request
    @ResponseStatus(HttpStatus.CREATED)
    User addNewUser(@RequestBody User user){
        return this.repository.save(user);
    }

    //Single items

    @GetMapping("/users/{id}")
    User getUser(@PathVariable Long id) throws UserNotFoundException {
        return this.repository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PostMapping("/users/{id}")
    User replaceUser (@RequestBody User newUser, @PathVariable Long id) throws UserNotFoundException {
        return this.repository.findById(id).map(
                user -> {
                    //TODO set user variables
                    return this.repository.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        this.repository.deleteById(id);
    }

}
