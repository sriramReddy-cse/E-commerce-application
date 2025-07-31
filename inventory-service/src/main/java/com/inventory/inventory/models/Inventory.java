package com.inventory.inventory.models;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "t_inventory")
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String skuCode;
     private Integer quantity;
}
