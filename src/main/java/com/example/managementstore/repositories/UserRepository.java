package com.example.managementstore.repositories;

import com.example.managementstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository extends JpaRepository to gain CRUD operations.
 * "User" is the entity type and "Long" is the ID type.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
