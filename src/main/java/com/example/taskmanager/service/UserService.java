package com.example.taskmanager.service;

import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository users;
    private final PasswordEncoder encoder;

    public UserService(UserRepository users,
                       PasswordEncoder encoder) {

        this.users = users;
        this.encoder = encoder;
    }

    public User register(String username,
                         String rawPassword){

        if(users.existsByUsername(username)){
            throw new IllegalArgumentException(
                    "Username already taken"
            );
        }

        User u = new User();

        u.setUsername(username);

        u.setPasswordHash(
                encoder.encode(rawPassword)
        );

        u.setRole("USER");

        return users.save(u);
    }

    public Optional<User> findByUsername(
            String username){

        return users.findByUsername(username);
    }

    public boolean verifyPassword(
            User user,
            String rawPassword){

        return encoder.matches(
                rawPassword,
                user.getPasswordHash()
        );
    }

    public Optional<UserDetails>
    loadUserByUsernameOpt(String username){

        return users.findByUsername(username)
                .map(u ->

                        org.springframework.security
                                .core.userdetails
                                .User

                                .withUsername(
                                        u.getUsername()
                                )

                                .password(
                                        u.getPasswordHash()
                                )

                                .authorities(
                                        List.of(
                                                new SimpleGrantedAuthority(
                                                        "ROLE_"+u.getRole()
                                                )
                                        )
                                )

                                .build()

                );
    }
}