package com.example.managementstore.repositories;

import com.example.managementstore.entities.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<ItemCategory, Long> {
    // Basic CRUD methods are already provided by JpaRepository
}
