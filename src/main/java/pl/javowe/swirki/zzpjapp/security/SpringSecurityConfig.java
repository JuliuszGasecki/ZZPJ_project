package pl.javowe.swirki.zzpjapp.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import pl.javowe.swirki.zzpjapp.controller.MyUserDetails;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetails myUserDetails;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
       /*
        http
                .authorizeRequests()
                .mvcMatchers("/").permitAll()
                .antMatchers("/css/**", "/js/**", "fonts/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/users/*").hasRole("ADMIN")
                .antMatchers("/hello").hasRole("USER")
                .antMatchers("/admins", "/removeAdmin/*", "/admin/*", "/setAdmin/*", "/deleteUser/*").hasRole("ADMIN")
        */
        //For not logged in
        http.authorizeRequests().antMatchers("/", "/login").permitAll().antMatchers("/css/**", "/js/**", "fonts/**").permitAll();
        //For Admins Only
        http.authorizeRequests().antMatchers("/admins", "/removeAdmin/*", "/admin/*", "/setAdmin/*", "/deleteUser/*").hasAnyRole("ADMIN", "USER");

        http.formLogin().defaultSuccessUrl("/users");

    }


    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(myUserDetails).
        passwordEncoder(passwordEncoder()).and()
        .authenticationProvider(authenticationProvider());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetails);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
/*
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                users
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build());
        manager.createUser(
                users
                        .username("root")
                        .password("toor")
                        .roles("ADMIN")
                        .build());
        return manager;
    }*/

}
