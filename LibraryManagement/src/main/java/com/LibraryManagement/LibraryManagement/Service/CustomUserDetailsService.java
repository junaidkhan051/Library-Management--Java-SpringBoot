package com.LibraryManagement.LibraryManagement.Service;

import com.LibraryManagement.LibraryManagement.Repository.UserRepo;

import com.LibraryManagement.LibraryManagement.entity.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepository;

    public CustomUserDetailsService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        user user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(

                user.getUsername(),

                user.getPassword(),

                List.of(
                        new SimpleGrantedAuthority(
                                "ROLE_" + user.getRole()
                        )
                )
        );
    }
}