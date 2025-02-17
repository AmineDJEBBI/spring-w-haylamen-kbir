package com.example.managementstore.services;

import com.example.managementstore.entities.PurchaseOrder;
import com.example.managementstore.entities.PurchaseOrderLineItem;
import com.example.managementstore.exceptions.ResourceNotFoundException;
import com.example.managementstore.repositories.ItemRepository;
import com.example.managementstore.repositories.PurchaseOrderLineItemRepository;
import com.example.managementstore.repositories.PurchaseOrderRepository;
import com.example.managementstore.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    private final ItemRepository itemRepository;
    private final StoreRepository storeRepository;
    private final PurchaseOrderLineItemRepository puchaseOrderLineItemRepository;


    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, ItemRepository itemRepository, StoreRepository storeRepository, PurchaseOrderLineItemRepository puchaseOrderLineItemRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.itemRepository = itemRepository;
        this.storeRepository = storeRepository;
        this.puchaseOrderLineItemRepository = puchaseOrderLineItemRepository;
    }

    // Create a new Purchase Order
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }


    // Update an existing Purchase Order
    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder purchaseOrder) throws ResourceNotFoundException {
        if (purchaseOrderRepository.existsById(id)) {
            purchaseOrder.setId(id);
            return purchaseOrderRepository.save(purchaseOrder);
        } else {
            throw new ResourceNotFoundException("Purchase Order not found");
        }
    }

    // Delete a Purchase Order by its ID
    public void deletePurchaseOrder(Long id) throws ResourceNotFoundException {
        if (purchaseOrderRepository.existsById(id)) {
            purchaseOrderRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Purchase Order not found");
        }
    }

    // Get all Purchase Orders
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    // Get a specific Purchase Order by ID
    public Optional<PurchaseOrder> getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id);
    }

    // Add an item to a purchase order, with a specified quantity
    public PurchaseOrderLineItem addItemToPurchaseOrder(Long purchaseOrderId, Long itemId, int quantity) throws ResourceNotFoundException {
        var purchaseOrder = purchaseOrderRepository.findById(purchaseOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase Order not found"));
        var item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        PurchaseOrderLineItem lineItem = new PurchaseOrderLineItem(item, quantity, purchaseOrder);
        puchaseOrderLineItemRepository.save(lineItem);
        purchaseOrderRepository.save(purchaseOrder);

        return lineItem;
    }

    // Get all purchase order line items by purchase order id
    public Set<PurchaseOrderLineItem> getPurchaseOrderLineItems(Long purchaseOrderId) throws ResourceNotFoundException {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(purchaseOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase Order not found"));
        var lineItems = puchaseOrderLineItemRepository.findAllByPurchaseOrderId(purchaseOrderId);
        return lineItems;
    }

    // Get all purchase orders line items
    public List<PurchaseOrderLineItem> getAllPurchaseOrderLineItems() {
        return puchaseOrderLineItemRepository.findAll();
    }

    public PurchaseOrderLineItem getPurchaseOrderLineItemById(Long lineItemId) throws ResourceNotFoundException {
        return puchaseOrderLineItemRepository.findById(lineItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase Order Line Item not found"));
    }

    // Delete a purchase order line item by its id
    public void deletePurchaseOrderLineItem(Long lineItemId) throws ResourceNotFoundException {
        if (puchaseOrderLineItemRepository.existsById(lineItemId)) {
            puchaseOrderLineItemRepository.deleteById(lineItemId);
        } else {
            throw new ResourceNotFoundException("Purchase Order Line Item not found");
        }
    }
}