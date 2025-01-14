package com.example.managementstore.controllers;

import com.example.managementstore.entities.Item;
import com.example.managementstore.entities.ItemCategory;
import com.example.managementstore.entities.Store;
import com.example.managementstore.entities.StoreInventory;
import com.example.managementstore.services.StoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indicates that the class is a RESTful web service controller
@RequestMapping("/api/v1/stores") // Maps all HTTP operations by default
@Tag(name = "Store", description = "Store API") // Swagger annotation
public class StoreController {

    @Autowired // Automatically injects StoreService into this controller
    private StoreService storeService;

    @GetMapping("/") // Maps to HTTP GET and fetches all stores
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @PreAuthorize("hasRole('ADMIN')") // Ensures only users with the role ADMIN can access this endpoint
    @PostMapping("/createStore") // Maps to HTTP POST and creates a new store
    public Store createStore(@RequestBody Store store) {
        return storeService.createStore(store);
    }

    // Update an existing store
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateStore/{id}") // Maps to HTTP PUT and updates an existing store
    public Store updateStore(@PathVariable Long id, @RequestBody Store store) {
        return storeService.updateStore(id, store);
    }

    // Delete a store
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteStore/{id}") // Maps to HTTP DELETE and removes a store
    public void deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
    }

    // Search for stores based on location
    @GetMapping("/search/location/{location}") // Maps to HTTP GET and fetches stores by location
    public List<Store> searchByLocation(@PathVariable String location) {
        return storeService.searchByLocation(location);
    }

    // Get a store by its ID
    @GetMapping("/getStore/{id}")
    public Store getStore(@PathVariable Long id) {
        var store = storeService.getStoreById(id);
        store.getStoreInventories().forEach(storeInventory -> {
            storeInventory.setStore(null);
            storeInventory.setItem(null);
        });

        return store;
    }

    // Get store inventory by store ID
    @GetMapping("/getStoreInventory/{id}")
    public List<StoreInventory> getStoreInventory(@PathVariable Long id) {
        List<StoreInventory> storeInventories = storeService.getStoreInventory(id);
        storeInventories.forEach(storeInventory -> {
            storeInventory.setStore(null);
            var item = storeInventory.getItem();
            var category = item.getCategory();
            storeInventory.setItem(
                    new Item(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getInitialQuantity(),
                            item.getQuantity(), new ItemCategory(category.getId(), category.getName(), category.getSupplier())));
        });

        return storeInventories;
    }


}
