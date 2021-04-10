package io.udemy.dougllas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder pEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!username.equals("tomtom")) {
            throw new UsernameNotFoundException("Usuario nao encontrado na base.");
        }

        return User
                .builder()
                .username("tomtom")
                .password(pEncoder.encode("111"))
                .roles("USER", "ADMIN")
                .build();
    }
}
