package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repositories.MenuItemRepository;
import com.example.demo.repositories.RestaurantRepository;
import com.example.demo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class RestaurantController {

    @Autowired
    RestaurantRepository resRepo;

    @Autowired
    UsersRepository userRepo;

    @Autowired
    MenuItemRepository itemRepo;

    @PostMapping("/addRestaurant")
    public ResponseEntity<?> addRestaurant(@RequestBody Restaurant restaurant, @CurrentUser UserPrinciple userPrinciple){
        User currenUser = userRepo.findById(userPrinciple.getID()).get();
        restaurant.setOwner(currenUser);
        resRepo.save(restaurant);
        return ResponseEntity.ok("Restaurant added");
    }

    @PutMapping("/addMenuItem")
    public ResponseEntity<?> addMenuItem(@RequestBody MenuItem item, @CurrentUser UserPrinciple userPrinciple){
       Restaurant currentRestaurant = resRepo.findByOwner_Id(userPrinciple.getID()).get();
       currentRestaurant.getMenu().add(item);
       resRepo.save(currentRestaurant);
       return ResponseEntity.ok("Menu item added!");
    }

    @DeleteMapping("/deleteRestaurant")
    public ResponseEntity<?> deleteRestaurant(@CurrentUser UserPrinciple principle){
        Restaurant currentRestaurant = resRepo.findByOwner_Id(principle.getID()).get();
        resRepo.delete(currentRestaurant);
        return ResponseEntity.ok("Restaurant deleted!");
    }

    @PutMapping("/deleteMenuItem/{id}")
    public ResponseEntity<?> deleteMenuItem(@PathVariable Long id, @CurrentUser UserPrinciple userPrinciple){
        Restaurant currentRestaurant = resRepo.findByOwner_Id(userPrinciple.getID()).get();
        MenuItem itemRemove = itemRepo.findById(id).get();
        currentRestaurant.getMenu().remove(itemRemove);
        itemRepo.delete(itemRemove);
        resRepo.save(currentRestaurant);
        return ResponseEntity.ok("Menu item deleted");
    }

    @PutMapping("/updateRestaurant")
    public ResponseEntity<?> updateRestaurant(@RequestBody Restaurant restaurant){
        Restaurant foundRestaurant = resRepo.findByID(restaurant.getID()).get();
        foundRestaurant.setAddress(restaurant.getAddress());
        foundRestaurant.setMenu(restaurant.getMenu());
        foundRestaurant.setName(restaurant.getName());
        foundRestaurant.setPhone(restaurant.getPhone());
        resRepo.save(foundRestaurant);
        return ResponseEntity.ok("Restaurant updated!");
    }

    @GetMapping("/getRestaurant")
    public Restaurant getRestaurant(@CurrentUser UserPrinciple principle){
        Optional<Restaurant> restaurant = resRepo.findByOwner_Id(principle.getID());
        return restaurant.orElse(null);
    }

    @GetMapping("/menuItems")
    public ResponseEntity<?> getMenuItems(@CurrentUser UserPrinciple principle){
        Optional<Restaurant> restaurant = resRepo.findByOwner_Id(principle.getID());
        return ResponseEntity.ok(restaurant.get().getMenu());
    }

    @GetMapping("/getRestaurantCheckout")
    public ResponseEntity<?> getRestaurantCheckout(){
        return ResponseEntity.ok(resRepo.findAll());
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<?> restaurantById(@PathVariable Long id){
        return ResponseEntity.ok(resRepo.findByID(id));
    }
}
