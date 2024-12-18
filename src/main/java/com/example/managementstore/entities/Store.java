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
public class Store  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @NonNull
    private String location;

    @NonNull
    private String contactInformation;

    @NonNull
    private String storeType;

    @NonNull
    private Date openingDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store", cascade = CascadeType.ALL)
    private List<StoreInventory> storeInventories = new ArrayList<>();

    public Store(Long id) {
        this.id = id;
    }
}

