package pl.javowe.swirki.zzpjapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserNotFoundException extends Exception {


    public UserNotFoundException(Long id) {
        super("Could not find user id = " + id);
    }
}
