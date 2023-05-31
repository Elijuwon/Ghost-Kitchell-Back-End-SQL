package com.example.demo.repositories;

import com.example.demo.model.Role;
import com.example.demo.model.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    Optional<Role> findByName(RoleName name);

}
