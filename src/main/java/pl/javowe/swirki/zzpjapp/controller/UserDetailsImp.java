package pl.javowe.swirki.zzpjapp.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.javowe.swirki.zzpjapp.model.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsImp implements UserDetails {

    User user;

    public UserDetailsImp(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        if(this.user.isAdmin()){
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        else{
            authorities.add(new SimpleGrantedAuthority("USER"));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        System.out.println(user.getPassword());
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
