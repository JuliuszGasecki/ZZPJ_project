package pl.javowe.swirki.zzpjapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javowe.swirki.zzpjapp.exception.UserInvalidDataException;
import pl.javowe.swirki.zzpjapp.exception.UserNotFoundException;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private Validator validator;
    private ValidatorFactory factory;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    public User save(User user) throws UserInvalidDataException
    {
        if (validateUser(user))
        {
            userRepository.save(user);
        }
        else
            throw new UserInvalidDataException(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) throws UserNotFoundException {
        User user;
        if (userRepository.existsById(userId))
            user = userRepository.findById(userId).get();
        else
            throw new UserNotFoundException(userId);
        return user;
    }

    @Override
    public void addUser(User user) throws UserInvalidDataException {
        if (validateUser(user))
            userRepository.save(user);
        else
            throw new UserInvalidDataException(user);
    }

    @Override
    public void deleteUser(Long userId) throws UserNotFoundException{
        if (userRepository.existsById(userId))
            userRepository.deleteById(userId);
        else
            throw new UserNotFoundException(userId);
    }

    @Override
    public void updateUser(User user) {
        if (userRepository.existsById(user.getId()))
        {
            ;
        }
    }

    @Override
    public void setAdmin(Long userId) throws UserNotFoundException {
        if(userRepository.findById(userId).isPresent()) {
            userRepository.findById(userId).get().setAdmin(true);
            userRepository.save(userRepository.findById(userId).get());
        }
        else throw new UserNotFoundException(userId);
    }

    @Override
    public void removeAdmin(Long userId) throws UserNotFoundException {
        if(userRepository.findById(userId).isPresent()) {
            userRepository.findById(userId).get().setAdmin(false);
            userRepository.save(userRepository.findById(userId).get());
        }
        else throw new UserNotFoundException(userId);
    }

    @Override
    public User getAdmin(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public List<User> getAllAdmins() {
        List<User> admins = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            if(user.isAdmin()) {
                admins.add(user);
            }
        }
        return admins;
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
