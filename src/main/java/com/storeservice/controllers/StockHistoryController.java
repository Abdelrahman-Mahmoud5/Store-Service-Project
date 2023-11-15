package com.storeservice.controllers;

import com.storeservice.dto.StockHistoryDto;
import com.storeservice.services.StockHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class StockHistoryController {
    @Autowired
    private StockHistoryService stockHistoryService;


    @GetMapping
    public ResponseEntity<List<StockHistoryDto>> getHistorys() {
        return new ResponseEntity<>(stockHistoryService.getHistory(), HttpStatus.OK);
    }

}
