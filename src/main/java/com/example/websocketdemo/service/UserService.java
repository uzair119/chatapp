package com.example.websocketdemo.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import  com.example.websocketdemo.model.User;
import  com.example.websocketdemo.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    //for spring security
    @Override
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        System.out.println("user");
        System.out.println(user.getUsername()+ " " +  " " + user.getPassword());
        Set<GrantedAuthority> grantedAuthorities = new HashSet();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

    public User findUser(String username)
    {
        return userRepository.findByUsername(username);
    }
    public boolean existsByUsername(String username)
    {
        if(userRepository.existsByUsername(username))
            return true;
        return false;
    }
    public User updateUser(User user)
    {
        if(userRepository.existsByUsername(user.getUsername()))
            return userRepository.save(user);
        else
            return null;
    }

}