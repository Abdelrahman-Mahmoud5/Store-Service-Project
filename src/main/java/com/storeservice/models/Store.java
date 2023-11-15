package com.storeservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String phone;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Stock> stocks;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<StockHistory> stockHistories;

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ",stocks=" + stocks+
                '}';
    }
}


