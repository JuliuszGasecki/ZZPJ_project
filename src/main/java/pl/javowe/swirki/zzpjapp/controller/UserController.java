package pl.javowe.swirki.zzpjapp.controller;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.javowe.swirki.zzpjapp.exception.UserNotFoundException;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;


@RestController
public class UserController {
    private UserRepository repository;
    private ValidatorFactory factory;
    private Validator validator;

    @Autowired
    public UserController(UserRepository repository){
        this.repository = repository;
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    //Root <- access to all records from repository

    @GetMapping("/users") //<- method for http Get request
    public List<User> getAllUsers(){
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

    public void validateAndAddUser(User user)
    {
        if (validateUser(user))
            repository.save(user);
    }

    private boolean validateUser(User user)
    {
        Set<ConstraintViolation<User>> violations;
        violations = validator.validate(user);
        if (violations.size() == 0)
            return true;
        else
            return false;
    }

}
