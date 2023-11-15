package com.storeservice.services;

import com.storeservice.dto.StockDto;
import com.storeservice.dto.StockHistoryDto;
import com.storeservice.mapper.StockHistoryMapper;
import com.storeservice.models.StockHistory;
import com.storeservice.reopsitorys.StockHistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StockHistoryService {

    @Autowired
    private StockHistoryRepository stockHistoryRepository;
    @Autowired
    private StockHistoryMapper stockHistoryMapper;


    public void addProductHistory(StockDto stock) {

        StockHistoryDto stockHistoryDto = StockHistoryDto.builder()
                .productCode(stock.getProductCode())
                .productName(stock.getProductName())
                .quantity(stock.getQuantity())
                .currentQuantity(stock.getQuantity())
                .date(new Date())
                .action("ADD")
                .storeId(stock.getStoreId())
                .build();
        StockHistory stockHistory = stockHistoryMapper.toEntity(stockHistoryDto);

        stockHistoryRepository.save(stockHistory);
        System.out.println(stockHistory);
    }

    public void updateProductHistory(StockDto stock, int quantity) {

        StockHistoryDto stockHistoryDto = StockHistoryDto.builder()
                .productCode(stock.getProductCode())
                .productName(stock.getProductName())
                .quantity(quantity)
                .previousQuantity(stock.getQuantity() - quantity)
                .currentQuantity(stock.getQuantity())
                .date(new Date())
                .action("UPDATE")
                .storeId(stock.getStoreId())
                .build();
        StockHistory stockHistory = stockHistoryMapper.toEntity(stockHistoryDto);
        stockHistoryRepository.save(stockHistory);
        System.out.println(stockHistory);
    }

    public void consumeProductHistory(StockDto stock, int quantity) {

        StockHistoryDto stockHistoryDto = StockHistoryDto.builder()
                .productCode(stock.getProductCode())
                .productName(stock.getProductName())
                .quantity(quantity)
                .previousQuantity(stock.getQuantity() + quantity)
                .currentQuantity(stock.getQuantity())
                .date(new Date())
                .action("CONSUME")
                .storeId(stock.getStoreId())
                .build();
        StockHistory stockHistory = stockHistoryMapper.toEntity(stockHistoryDto);
        stockHistoryRepository.save(stockHistory);
        System.out.println(stockHistory);
    }

    public List<StockHistoryDto> getHistorys() {
        return stockHistoryMapper.listDto(stockHistoryRepository.findAll());
    }

}

