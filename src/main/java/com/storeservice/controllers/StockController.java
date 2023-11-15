package com.storeservice.controllers;

import com.storeservice.dto.StockDto;
import com.storeservice.exceptions.ThrowExceptionMessage;
import com.storeservice.services.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<Boolean> checkProductAvailability(@RequestParam String productCode, @RequestParam Long storeId, @RequestParam int requiredQuantity) throws ThrowExceptionMessage {

        boolean isAvailable = stockService.isProductAvailable(productCode, storeId, requiredQuantity);
        return ResponseEntity.ok(isAvailable);
    }

    @PostMapping
    public ResponseEntity<String> addStock(@RequestBody @Valid StockDto stockDto) throws ThrowExceptionMessage {
        return stockService.addStock(stockDto);
    }

    @PutMapping("/")
    public ResponseEntity<String> updateStock(@RequestBody @Valid StockDto stockDto) throws ThrowExceptionMessage {
        return stockService.updateStock(stockDto);
    }

    @PutMapping
    public ResponseEntity<String> consumeStock(@RequestParam String productCode, @RequestParam int quantity, @RequestParam Long soreId) throws ThrowExceptionMessage {
        return stockService.consumeStock(productCode, quantity, soreId);
    }
}



