package pl.javowe.swirki.zzpjapp.exception;

import pl.javowe.swirki.zzpjapp.model.User;

public class UserInvalidDataException extends Exception {
    public UserInvalidDataException(User user) {
        super("Could not add new User = " + user.getFirstName() + " " + user.getLastName());
    }
}
