package com.example.managementstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StoreInventory  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId", referencedColumnName = "id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId", referencedColumnName = "id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String status;

    public StoreInventory(Store store, Item item, int quantity, String status) {
        this.store = store;
        this.quantity = quantity;
        this.item = item;
        this.status = status;
    }

    public void addItems(int count) {
        this.quantity += count;
    }

    public void removeItems(int count) {
        this.quantity -= count;
    }

    // add status column field request
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * Checks if the inventory level is below a certain threshold.
     *
     * @param threshold The threshold to check against.
     * @return True if the quantity is below the threshold, false otherwise.
     */
    public boolean isBelowThreshold(int threshold) {
        return this.quantity < threshold;
    }

}

