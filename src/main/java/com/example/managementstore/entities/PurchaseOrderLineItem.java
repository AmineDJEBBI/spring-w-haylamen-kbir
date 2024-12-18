package com.example.managementstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.*;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PurchaseOrderLineItem  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId", referencedColumnName = "id")
    private Item item;

    @NonNull
    @NotNull
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_id", referencedColumnName = "id")
    @NonNull
    @NotNull
    private PurchaseOrder purchaseOrder;

    public PurchaseOrderLineItem(Item item, int quantity, PurchaseOrder purchaseOrder) {
        this.item = item;
        this.quantity = quantity;
        this.purchaseOrder = purchaseOrder;
    }
}

