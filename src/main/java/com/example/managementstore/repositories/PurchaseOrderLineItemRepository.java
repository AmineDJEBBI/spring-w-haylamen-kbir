package com.example.managementstore.repositories;

import com.example.managementstore.entities.PurchaseOrderLineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * PurchaseOrderRepository extends JpaRepository to gain CRUD operations.
 * "PurchaseOrder" is the entity type and "Long" is the ID type.
 */

@Repository
public interface PurchaseOrderLineItemRepository extends JpaRepository<PurchaseOrderLineItem, Long> {
    Set<PurchaseOrderLineItem> findAllByPurchaseOrderId(Long purchaseOrderId);
}
