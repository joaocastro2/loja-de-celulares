package br.com.phone.store.tables.users.service;

import br.com.phone.store.tables.users.model.UsersModel;
import br.com.phone.store.tables.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Custom implementation of {@link UserDetailsService} used by Spring Security
 * to load user-specific data during authentication.
 *
 * <p>This service retrieves user information from the database using the {@link UsersRepository}
 * and converts it into a {@link org.springframework.security.core.userdetails.User} object
 * compatible with Spring Security's authentication framework.</p>
 */
@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UsersRepository repository;

    /**
     * Loads a user by their email address, which serves as the username.
     *
     * <p>If the user is found, returns a Spring Security {@link UserDetails} object
     * containing the user's email, password, and an empty list of authorities.</p>
     *
     * @param username The user's email address.
     * @return A {@link UserDetails} object for authentication.
     * @throws UsernameNotFoundException if no user is found with the given email.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersModel user = this.repository.findByUserEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("USER NOT FOUND"));

        return new org.springframework.security.core.userdetails.User(
                user.getUserEmail(), user.getUserPassword(), new ArrayList<>());
    }
}
