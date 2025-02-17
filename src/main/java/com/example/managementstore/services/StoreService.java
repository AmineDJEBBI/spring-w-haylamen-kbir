package com.example.managementstore.services;

import com.example.managementstore.entities.Store;
import com.example.managementstore.entities.StoreInventory;
import com.example.managementstore.exceptions.ResourceNotFoundException;
import com.example.managementstore.repositories.StoreInventoryRepository;
import com.example.managementstore.repositories.StoreRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreInventoryRepository storeInventoryRepository;

    public StoreService(StoreRepository storeRepository, StoreInventoryRepository storeInventoryRepository) {
        this.storeRepository = storeRepository;
        this.storeInventoryRepository = storeInventoryRepository;
    }

    // Create a new Store
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    // Update an existing Store
    public Store updateStore(Long id, Store store) throws ResourceNotFoundException {
        if (storeRepository.existsById(id)) {
            store.setId(id);
            return storeRepository.save(store);
        } else {
            throw new ResourceNotFoundException("Store not found");
        }
    }

    // Delete a Store by its ID
    public void deleteStore(Long id) throws ResourceNotFoundException {
        if (storeRepository.existsById(id)) {
            // Retrieve all store inventories related to the store
            List<StoreInventory> inventories = storeInventoryRepository.findAllByStoreId(id);
            // Delete each store inventory entry
            for (StoreInventory inventory : inventories) {
                storeInventoryRepository.deleteById(inventory.getId());
            }
            // Delete the store itself
            storeRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Store not found");
        }
    }

    // Get a Store by its ID
    @Transactional
    public Store getStoreById(Long id) throws ResourceNotFoundException {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));

        List<StoreInventory> inventory = storeInventoryRepository.findAllByStoreId(store.getId());

        Hibernate.initialize(inventory);

        store.setStoreInventories(inventory);

        return store;
    }

    // Get store inventory by store ID
    public List<StoreInventory> getStoreInventory(Long id) throws ResourceNotFoundException {
        if (storeRepository.existsById(id)) {
            return storeInventoryRepository.findAllByStoreId(id);
        } else {
            throw new ResourceNotFoundException("Store not found");
        }
    }

    // Get all Stores
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    // Additional functionalities
    public List<Store> searchByLocation(String location) {
        return storeRepository.findByLocationContainingIgnoreCase(location);
    }

    public List<Store> searchByName(String name) {
        return storeRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Store> searchByType(String storeType) {
        return storeRepository.findByStoreTypeContainingIgnoreCase(storeType);
    }


    // search for store after opening date
    public List<Store> searchByOpeningDateAfter(Date date) {
        // convert from local date to date


        return storeRepository.findByOpeningDateAfter(date);
    }
}
