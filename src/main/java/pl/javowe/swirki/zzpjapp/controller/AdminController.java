package pl.javowe.swirki.zzpjapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.javowe.swirki.zzpjapp.exception.UserInvalidDataException;
import pl.javowe.swirki.zzpjapp.exception.UserNotFoundException;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.service.UserService;

import java.util.List;

@RestController
public class AdminController {
    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admins")
    public List<User> getAllAdmins() {
        return userService.getAllAdmins();
    }

    @GetMapping("/admin/{id}")
    public User getAdmin(@PathVariable Long id) throws UserNotFoundException {
        return userService.getAdmin(id);
    }

    @PutMapping("/setAdmin/{id}")
    public User setAdmin(@PathVariable Long id) throws UserNotFoundException, UserInvalidDataException {
        userService.setAdmin(id);
        return userService.getUser(id);
    }

    @PutMapping("/removeAdmin/{id}")
    public User removeAdmin(@PathVariable Long id) throws UserNotFoundException, UserInvalidDataException {
        userService.removeAdmin(id);
        return userService.getUser(id);
    }

    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user) throws UserInvalidDataException {
        //return this.repository.save(user);
        return this.userService.save(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUser(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUser(id);
    }
}
