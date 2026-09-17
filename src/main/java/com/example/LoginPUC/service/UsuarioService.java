package com.example.LoginPUC.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final InMemoryUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            InMemoryUserDetailsManager userDetailsManager,
            PasswordEncoder passwordEncoder) {

        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    public void cadastrar(String username, String password) {

        if (userDetailsManager.userExists(username)) {
            throw new RuntimeException("Usuário já cadastrado");
        }

        UserDetails usuario = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();

        userDetailsManager.createUser(usuario);
    }
}
