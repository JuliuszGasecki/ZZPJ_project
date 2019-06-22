package pl.javowe.swirki.zzpjapp.service;

import pl.javowe.swirki.zzpjapp.exception.UserInvalidDataException;
import pl.javowe.swirki.zzpjapp.exception.UserNotFoundException;
import pl.javowe.swirki.zzpjapp.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User getUser(Long userId) throws UserNotFoundException;
    public User save(User user) throws UserInvalidDataException;
    public void addUser(User user) throws UserInvalidDataException;
    public void deleteUser(Long userId) throws UserNotFoundException;
    public void updateUser(User user);
}
