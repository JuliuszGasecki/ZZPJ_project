package pl.javowe.swirki.zzpjapp.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import pl.javowe.swirki.zzpjapp.exception.UserInvalidDataException;
import pl.javowe.swirki.zzpjapp.exception.UserNotFoundException;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;
import pl.javowe.swirki.zzpjapp.service.ImageServiceImpl;
import pl.javowe.swirki.zzpjapp.service.UserService;
import pl.javowe.swirki.zzpjapp.service.UserServiceImpl;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.List;
import lombok.Data;


@RestController
public class UserController{
    private UserRepository repository;
    private UserService userService;
    private ValidatorFactory factory;
    private Validator validator;
    private ImageServiceImpl imageServiceImpl;
    private static final Logger LOGGER = LogManager.getLogger(ImageServiceImpl.class);

    @Autowired
    public UserController(UserRepository repository, ImageServiceImpl imageServiceImpl){
        this.repository = repository;
        userService = new UserServiceImpl(repository);
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        this.imageServiceImpl = imageServiceImpl;
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


    @PutMapping("/user/{id}/addImage/{filename}")
    public byte[] addImageForUser(@PathVariable Long id, @PathVariable("filename") String filename) throws UserNotFoundException, UserInvalidDataException {
        boolean flag = false;
        try {
            this.userService.getUser(id).setLoadedPicture(imageServiceImpl.saveImageToByte(filename));
            flag = true;
        } catch (IOException e) {
            LOGGER.error("Can not read file: " + filename);
            e.printStackTrace();
        }
        if(flag) {
            LOGGER.info("Image: " + filename + " - loaded");
        }
        this.userService.save(this.userService.getUser(id));
        return getImageForUser(id);
    }

    @GetMapping("/user/{id}/getImage")
    public byte[] getImageForUser(@PathVariable Long id) throws UserNotFoundException {
        return this.userService.getUser(id).getLoadedPicture();
    }

    @PutMapping("user/{id}/deleteImage")
    public void deleteImageForUser(@PathVariable Long id) throws UserNotFoundException, UserInvalidDataException {
        byte[] empty = new byte[0];
        this.userService.getUser(id).setLoadedPicture(empty);
        this.userService.save(this.userService.getUser(id));
    }
}
