package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UsersRepository userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @PutMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        UserPrinciple currentUser = (UserPrinciple) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(currentUser);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        if(userRepo.existsUserByUsername(user.getUsername())){
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER).get();
        user.setRoles(Collections.singleton(userRole));
        userRepo.save(user);
        return ResponseEntity.ok("Successfully created!");
    }

    @GetMapping("/getUser")
    public User getUser(@RequestBody User user) {
        Optional<User> foundUser = userRepo.findByUsername(user.getUsername());
        return foundUser.orElse(null);
    }

    @PutMapping("/updateUser")
    public Optional<User> editUser(@RequestBody User user)
    {
        Optional<User> founduser = userRepo.findById(user.getId());
        founduser.get().setEmail(user.getEmail());
        founduser.get().setUsername(user.getUsername());
        founduser.get().setPassword(user.getPassword());
        founduser.get().setFirstName(user.getFirstName());
        founduser.get().setLastName(user.getLastName());
        founduser.get().setPhone(user.getPhone());
        userRepo.save(founduser.get());
        return founduser;
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestBody User user){

        userRepo.deleteByUsername(user.getUsername());
        return "User Deleted";
    }

    @GetMapping("/currentUser")
    public ResponseEntity<?> currentUser(@CurrentUser UserPrinciple principle){
        return ResponseEntity.ok(userRepo.findById(principle.getID()));
    }

    @PutMapping("/logout")
    public void logout(){
        SecurityContextHolder.clearContext();
    }

}
