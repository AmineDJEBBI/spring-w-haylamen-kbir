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
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Set<PurchaseOrderLineItem> purchaseOrderLineItems = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId", referencedColumnName = "id", nullable = false)
    private Store store;

    public PurchaseOrder(Long id, @NonNull String status, Set<PurchaseOrderLineItem> purchaseOrderLineItems, Store store) {
        this.id = id;
        this.status = status;
        this.purchaseOrderLineItems = purchaseOrderLineItems;
        this.store = store;
    }

    public PurchaseOrder(Long id) {
        this.id = id;
    }

    public PurchaseOrder(Long id, String status) {
        this.id = id;
        this.status = status;
    }


    public void addPurchaseOrderLineItem(PurchaseOrderLineItem purchaseOrderLineItem) {
        purchaseOrderLineItems.add(purchaseOrderLineItem);
    }

    // override tostring method
    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "id=" + id +
                ", status='" + status + '\'' +
                // ", purchaseOrderLineItems=" + purchaseOrderLineItems +
                // ", store=" + store +
                '}';
    }
}

