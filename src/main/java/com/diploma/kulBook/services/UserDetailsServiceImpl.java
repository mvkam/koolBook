package com.diploma.kulBook.services;

import com.diploma.kulBook.entities.CustUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CustUserService userService;

    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        CustUser customUser = userService.findByLogin(login);
        if (customUser == null)
            throw new UsernameNotFoundException(login + " not found");

        List<GrantedAuthority> roles =
                Collections.singletonList(new SimpleGrantedAuthority(customUser.getRole().toString()));

        return new User(customUser.getLogin(),
                customUser.getPassword(), roles);
    }
}