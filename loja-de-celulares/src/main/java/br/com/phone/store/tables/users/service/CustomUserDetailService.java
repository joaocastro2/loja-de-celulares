package br.com.phone.store.tables.users.service;

import br.com.phone.store.tables.users.model.UsersModel;
import br.com.phone.store.tables.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UsersRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersModel user = this.repository.findByUserEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("USER NOT FOUND"));

        return new org.springframework.security.core.userdetails.User(
                user.getUserEmail(), user.getUserPassword(), new ArrayList<>());
    }
}
