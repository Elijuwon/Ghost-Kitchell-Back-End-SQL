package com.example.demo.controller;

import com.example.demo.model.Address;
import com.example.demo.model.User;
import com.example.demo.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    @Autowired
    AddressRepository addRepo;

    @PostMapping("/createAddress")
    public boolean register(@RequestBody Address address) {
        addRepo.save(address);
        return true;
    }
}
