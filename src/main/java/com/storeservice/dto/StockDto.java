package com.storeservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockDto {

    @NotNull(message = "productCode required")
    private String productCode;
    @NotNull(message = "productName required")
    private String productName;
    @Min(value = 1, message = "quantity must be greater than or equal to 1")
    private int quantity;
    @Min(value = 1, message = "storeId required")
    private Long storeId;

}
