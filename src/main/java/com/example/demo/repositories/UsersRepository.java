package com.example.demo.repositories;

import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername (String username);
    boolean deleteByUsername(String username);
    Optional<User> findById(long Id);
    boolean existsUserByUsername(String username);

}
