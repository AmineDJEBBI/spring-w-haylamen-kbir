package com.example.managementstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemCategory   {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String supplier;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    public ItemCategory(Long id) {
        this.id = id;
    }

    public ItemCategory(Long id, String name, String supplier) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
    }

    public void addItem(Item item) {
        items.add(item);
        item.setCategory(this);
    }

    @Override
    public String toString() {
        return "ItemCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}

