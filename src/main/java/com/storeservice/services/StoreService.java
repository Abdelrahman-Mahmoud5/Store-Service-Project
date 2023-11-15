package com.storeservice.services;

import com.storeservice.dto.StoreDto;
import com.storeservice.mapper.StoreMapper;
import com.storeservice.models.Store;
import com.storeservice.reopsitorys.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreMapper storeMapper;

    public ResponseEntity<String> createStore(StoreDto storeDto) {
        return Optional.ofNullable(storeRepository.findByName(storeDto.getName()))
                .map(store -> ResponseEntity.badRequest().body("A store with the name " + storeDto.getName() + " already exists"))
                .orElseGet(() -> {
                    Store store = storeMapper.toEntity(storeDto);
                    storeRepository.save(store);
                    return ResponseEntity.ok("store successfully added");
                });
    }

    public List<StoreDto> getStores() {
        return storeMapper.listDto(storeRepository.findAll());
    }

    public ResponseEntity<?> getStoreById(Long storeId) {
        Optional<Store> store = storeRepository.findById(storeId);
        if (store.isEmpty()){
            return ResponseEntity.badRequest().body("no store exist with ID [" + storeId+"]");
        }else {
            StoreDto storeDto = storeMapper.toDto(store.get());
            return ResponseEntity.ok(storeDto);
        }

    }

    public ResponseEntity<?> deleteStoreById(Long storeId) {
        Optional<Store> store = storeRepository.findById(storeId);
        if (store.isEmpty()){
            return ResponseEntity.badRequest().body("no store exist with ID [" + storeId+"]");
        }else {
            StoreDto storeDto = storeMapper.toDto(store.get());
            return ResponseEntity.ok(storeDto);
        }
    }

}
