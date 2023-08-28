package com.example.bookstoreproject.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.bookstoreproject.entity.User;
import com.example.bookstoreproject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String admin = "admin";

        User user = userRepository.findUserByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        if(admin.equals(user.getRole())) {
            authoritiesList.add(new SimpleGrantedAuthority("admin"));
        }

        return JwtUser.builder()
            .email(user.getEmail())
            .name(user.getName())
            .authorities(authoritiesList)
            .password(user.getPassword())
            .build();
    }

}
