package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserPrinciple;
import com.example.demo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CostumUserDetailsService implements UserDetailsService {
    @Autowired
    UsersRepository userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername (String s) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(s).get();
        return UserPrinciple.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long Id){
        User user = userRepo.findById(Id).get();
        return UserPrinciple.create(user);
    }
}
