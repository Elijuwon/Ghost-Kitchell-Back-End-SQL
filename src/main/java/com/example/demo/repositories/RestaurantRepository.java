package com.example.demo.repositories;

import com.example.demo.model.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository  extends CrudRepository<Restaurant, Long> {
    Optional<Restaurant> findByID(Long Id);
    Optional<Restaurant> findByOwner_Id(Long Id);
    Optional<Restaurant> findByName(String name);
    Optional<Restaurant> queryAllByID(Long id);

}
