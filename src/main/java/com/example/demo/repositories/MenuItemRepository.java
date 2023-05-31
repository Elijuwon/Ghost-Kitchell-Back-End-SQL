package com.example.demo.repositories;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
Optional<MenuItem> findByMenuItem(String menuItem);
}
