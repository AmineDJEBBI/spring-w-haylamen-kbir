package com.example.managementstore.repositories;

import com.example.managementstore.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ItemRepository extends JpaRepository to gain CRUD operations.
 * "Item" is the entity type and "Long" is the ID type.
 */

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByNameContainingIgnoreCase(String name);

    @Query("SELECT i FROM Item i WHERE i.category.name LIKE %:name%")
    List<Item> findByCategoryNameContaining(@Param("name") String name);
    List<Item> findByPriceBetween(double minPrice, double maxPrice);

    List<Item> findByPriceLessThan(double price);

    List<Item> findByPriceGreaterThan(double price);



   // List<Item> findByNameContainingAndCategoryContaining(String name, String category);
}
