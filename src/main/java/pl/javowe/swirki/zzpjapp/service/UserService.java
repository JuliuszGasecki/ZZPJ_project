package pl.javowe.swirki.zzpjapp.service;

import pl.javowe.swirki.zzpjapp.exception.UserInvalidDataException;
import pl.javowe.swirki.zzpjapp.exception.UserNotFoundException;
import pl.javowe.swirki.zzpjapp.model.User;

import java.util.List;

public interface UserService {
     List<User> getAllUsers();
     User getUser(Long userId) throws UserNotFoundException;
     User save(User user) throws UserInvalidDataException;
     void addUser(User user) throws UserInvalidDataException;
     void deleteUser(Long userId) throws UserNotFoundException;
     void updateUser(User user);
}
