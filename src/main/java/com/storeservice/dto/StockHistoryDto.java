package com.storeservice.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
public class StockHistoryDto {

    private String productCode;
    private String productName;
    private int quantity;
    private int previousQuantity;
    private int currentQuantity;
    private String action;
    private Date date;
    private Long storeId;

}
