package com.example.managementstore.repositories;

import com.example.managementstore.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RoleRepository interface for Role entity.
 *
 * This interface inherits from JpaRepository, which provides methods
 * to perform CRUD operations (Create, Read, Update, Delete) as well as
 * pagination and sorting. By extending JpaRepository, this interface
 * will have methods like findAll(), save(), delete(), etc., automatically
 * implemented at runtime by Spring Data JPA.
 *
 * The first generic parameter "Role" indicates the domain type the repository manages,
 * and the second generic parameter "Long" indicates the type of the id of the entity.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
