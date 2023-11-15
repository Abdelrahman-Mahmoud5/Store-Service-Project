package com.storeservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.storeservice.models.Stock;
import com.storeservice.models.StockHistory;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Builder
public class StoreDto {

    @NotEmpty(message = "name required")
    private String name;
    @NotNull(message = "location required")
    private String location;
    private String phone;
    @JsonIgnore
    private List<Stock> stocks;
    @JsonIgnore
    private List<StockHistory> stockHistories;

}
