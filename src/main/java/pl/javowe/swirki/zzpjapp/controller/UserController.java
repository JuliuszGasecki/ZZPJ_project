package pl.javowe.swirki.zzpjapp.controller;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.javowe.swirki.zzpjapp.exception.UserInvalidDataException;
import pl.javowe.swirki.zzpjapp.exception.UserNotFoundException;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;
import pl.javowe.swirki.zzpjapp.service.UserService;
import pl.javowe.swirki.zzpjapp.service.UserServiceImpl;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;


@RestController
public class UserController {
    private UserRepository repository;
    private UserService userService;
    private ValidatorFactory factory;
    private Validator validator;

    @Autowired
    public UserController(UserRepository repository){
        this.repository = repository;
        userService = new UserServiceImpl(repository);
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    //Root <- access to all records from repository

    @GetMapping("/users") //<- method for http Get request
    public List<User> getAllUsers(){
        //return this.repository.findAll();
        return this.userService.getAllUsers();
    }

    @PostMapping("/users") //<- method for http Post request
    @ResponseStatus(HttpStatus.CREATED)
    User addNewUser(@RequestBody User user) throws UserInvalidDataException {
        //return this.repository.save(user);
        return this.userService.save(user);
    }

    //Single items

    @GetMapping("/users/{id}")
    User getUser(@PathVariable Long id) throws UserNotFoundException {
        //return this.repository.findById(id)
                //.orElseThrow(()->new UserNotFoundException(id));
        return this.userService.getUser(id);
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
    void deleteUser(@PathVariable Long id) throws UserNotFoundException {
        //this.repository.deleteById(id);
        this.userService.deleteUser(id);
    }


}
