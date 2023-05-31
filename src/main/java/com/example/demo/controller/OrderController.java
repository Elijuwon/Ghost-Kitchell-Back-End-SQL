package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repositories.MenuItemRepository;
import com.example.demo.repositories.OrderRespository;
import com.example.demo.repositories.UsersRepository;
import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class OrderController {
    @Autowired
    UsersRepository userRepo;

    @Autowired
    OrderRespository orderRepo;

    @Autowired
    MenuItemRepository itemRepo;

    @PostMapping("/addOrder")
    public ResponseEntity<?> addOrder (@CurrentUser UserPrinciple userPrinciple){
        User currenUser = userRepo.findById(userPrinciple.getID()).get();
        CustomerOrder order = new CustomerOrder();
        currenUser.setCurrentOrder(order);
        orderRepo.save(order);
        return ResponseEntity.ok(order.getID());
    }

    @PutMapping("/addOrderItem")
    public ResponseEntity<?> addOrderItem(@CurrentUser UserPrinciple userPrinciple,@RequestBody MenuItem item){
        User currentUser = userRepo.findById(userPrinciple.getID()).get();
        currentUser.getCurrentOrder().getOrderitems().add(item);
        userRepo.save(currentUser);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/getTotal")
    public ResponseEntity<?> getGrandTotal( @CurrentUser UserPrinciple userPrinciple){
        User currentUser = userRepo.findById(userPrinciple.getID()).get();
        CustomerOrder currentOrder = currentUser.getCurrentOrder();

        double subTotal = 0;
        for (int i =0; i < currentOrder.getOrderitems().size(); i++){
            subTotal += currentOrder.getOrderitems().get(i).getPrice().doubleValue();
        }
      return  ResponseEntity.ok((subTotal * .08875) + subTotal);
    }

    @PutMapping("/deleteOrderItem")
    public ResponseEntity<?> deleteOrderItem( @CurrentUser UserPrinciple userPrinciple,@RequestBody MenuItem item){
        User currenUser = userRepo.findById(userPrinciple.getID()).get();
        CustomerOrder currentOrder = currenUser.getCurrentOrder();
        currentOrder.getOrderitems().removeIf(menuItem -> menuItem.getId().equals(item.getId()));
        currenUser.setCurrentOrder(currentOrder);

        System.out.println(currentOrder.getOrderitems() + " " + currentOrder.getOrderitems().contains(item));
        userRepo.save(currenUser);
        return ResponseEntity.ok("Item deleted!");
    }

    @GetMapping("/getCurrentOrder")
    public ResponseEntity<?> restaurantById(@CurrentUser UserPrinciple principle){
        User currenUser = userRepo.findById(principle.getID()).get();

        return ResponseEntity.ok(orderRepo.findById(currenUser.getCurrentOrder().getID()).get());
    }

}
