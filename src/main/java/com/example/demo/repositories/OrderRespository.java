package com.example.demo.repositories;

import com.example.demo.model.CustomerOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRespository extends CrudRepository<CustomerOrder, Long> {
}
