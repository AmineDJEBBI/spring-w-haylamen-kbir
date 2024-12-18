package com.example.managementstore.repositories;

import com.example.managementstore.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PurchaseOrderRepository extends JpaRepository to gain CRUD operations.
 * "PurchaseOrder" is the entity type and "Long" is the ID type.
 */
@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
