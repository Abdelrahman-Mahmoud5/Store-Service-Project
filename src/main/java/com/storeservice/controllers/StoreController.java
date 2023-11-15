package com.storeservice.controllers;

import com.storeservice.dto.StoreDto;
import com.storeservice.mapper.StoreMapper;
import com.storeservice.models.Store;
import com.storeservice.reopsitorys.StoreRepository;
import com.storeservice.services.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreMapper storeMapper;

    @GetMapping
    public ResponseEntity<List<StoreDto>> getStores() {
        return new ResponseEntity<>(storeService.getStores(), HttpStatus.OK);
    }
    @GetMapping("/{storeId}")
    public ResponseEntity<?> getStoreById(@PathVariable Long storeId){
        return storeService.getStoreById(storeId);
    }

    @PostMapping("/")
    public ResponseEntity<String> createStore(@RequestBody @Valid StoreDto storeDto) {
        return storeService.createStore(storeDto);
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<?> deleteStore(@PathVariable Long storeId) {
        return storeService.deleteStoreById(storeId);
    }



}




