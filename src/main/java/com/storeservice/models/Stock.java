package com.storeservice.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productCode;
    private String productName;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
