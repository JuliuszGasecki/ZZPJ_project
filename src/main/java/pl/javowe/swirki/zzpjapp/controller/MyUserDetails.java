package pl.javowe.swirki.zzpjapp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.javowe.swirki.zzpjapp.model.User;
import pl.javowe.swirki.zzpjapp.repository.UserRepository;
import pl.javowe.swirki.zzpjapp.service.UserServiceImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            LOGGER.error("User: " + username + " - NOT FOUND");
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImp(user);
    }
}
