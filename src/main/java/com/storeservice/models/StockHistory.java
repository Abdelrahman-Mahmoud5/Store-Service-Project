package com.storeservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@Table(name = "stock_history")

public class StockHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productCode;
    private String productName;
    private int quantity;
    private int previousQuantity;
    private int currentQuantity;
    private String action;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "store_Id")
    private Store store;

    @Override
    public String toString() {
        return "StockHistory{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", previousQuantity=" + previousQuantity +
                ", currentQuantity=" + currentQuantity +
                ", action='" + action + '\'' +
                ", date=" + date +
                '}';
    }
}
