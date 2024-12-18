package com.example.managementstore.repositories;

import com.example.managementstore.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * StoreRepository extends JpaRepository to gain CRUD operations.
 * "Store" is the entity type and "Long" is the ID type.
 */

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByStoreTypeContainingIgnoreCase(String storeType);

    List<Store> findByLocationContainingIgnoreCase(String location);

    List<Store> findByNameContainingIgnoreCase(String name);

    List<Store> findByOpeningDateAfter(Date openingDate);
}
