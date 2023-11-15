package com.storeservice.services;

import com.storeservice.dto.StockDto;
import com.storeservice.dto.StoreDto;
import com.storeservice.exceptions.ThrowExceptionMessage;
import com.storeservice.mapper.StockMapper;
import com.storeservice.mapper.StoreMapper;
import com.storeservice.models.Stock;
import com.storeservice.models.Store;
import com.storeservice.reopsitorys.StockRepository;
import com.storeservice.reopsitorys.StoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class StockService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private StockHistoryService stockHistoryService;
    @Autowired
    private StoreRepository storeRepository;

    public ResponseEntity<String> addStock(StockDto stockDto) throws ThrowExceptionMessage {
        StoreDto store = checkStoreById(stockDto.getStoreId());

        boolean productExists = store.getStocks().stream()
                .anyMatch(stock ->
                        stock.getProductCode().equals(stockDto.getProductCode()));

        if (productExists) {
            return ResponseEntity.badRequest().body("product with code: [" + stockDto.getProductCode() + "] already exist");

        } else {
            StockDto newStockDto = StockDto.builder()
                    .productCode(stockDto.getProductCode())
                    .productName(stockDto.getProductName())
                    .quantity(stockDto.getQuantity())
                    .storeId(stockDto.getStoreId())
                    .build();
            Stock newStock = stockMapper.toEntity(newStockDto);

            stockRepository.save(newStock);
            stockHistoryService.addProductHistory(newStockDto);
            return ResponseEntity.ok("product successfully added");
        }
    }

    public ResponseEntity<String> updateStock(StockDto stockDto) throws ThrowExceptionMessage {
        StoreDto store = checkStoreById(stockDto.getStoreId());

        boolean productExists = store.getStocks().stream()
                .anyMatch(stock ->
                        stock.getProductCode().equals(stockDto.getProductCode()));

        if (!productExists) {
            return ResponseEntity.badRequest().body("product with code: [" + stockDto.getProductCode() + "] isn't exist");

        } else {
            StockDto newStockDto = StockDto.builder()
                    .productCode(stockDto.getProductCode())
                    .productName(stockDto.getProductName())
                    .quantity(stockDto.getQuantity())
                    .storeId(stockDto.getStoreId())
                    .build();
            Stock newStock = stockMapper.toEntity(newStockDto);

            stockRepository.save(newStock);
            stockHistoryService.updateProductHistory(newStockDto, stockDto.getQuantity());
            return ResponseEntity.ok("product successfully updated");
        }
    }

    public ResponseEntity<String> consumeStock(String productCode, int quantity, Long storeId) throws ThrowExceptionMessage {
        checkStoreById(storeId);
        Optional<Stock> stock = stockRepository.findByProductCode(productCode);
        if (stock.isEmpty()) {
            return ResponseEntity.badRequest().body("product with code ["+productCode+"] not exist");
        } else if (stock.get().getQuantity() < quantity) {
            return ResponseEntity.badRequest().body("quantity not enough");
        } else {
            Stock newStock = stock.get();
            int currentQuantity = newStock.getQuantity();
            newStock.setQuantity(currentQuantity - quantity);
            stockHistoryService.consumeProductHistory(stockMapper.toDto(newStock), quantity);
            stockRepository.save(newStock);
            return ResponseEntity.ok("product successfully updated");
        }
    }

    public StoreDto checkStoreById(Long storeId) throws ThrowExceptionMessage {
        Optional<Store> store = storeRepository.findById(storeId);
        store.orElseThrow(() -> new ThrowExceptionMessage("Store not found"));
        return storeMapper.toDto(store.get());
    }

    public boolean isProductAvailable(String productCode, Long storeId, int quantity) throws ThrowExceptionMessage {
        Optional<Store> storeOptional = storeRepository.findById(storeId);

        if (storeOptional.isPresent()) {
            Store store = storeOptional.get();

            return store.getStocks().stream()
                    .anyMatch(stock -> stock.getProductCode().equals(productCode) && stock.getQuantity() >= quantity);

        } else {
            throw new ThrowExceptionMessage("Store not found");
        }
    }
}

