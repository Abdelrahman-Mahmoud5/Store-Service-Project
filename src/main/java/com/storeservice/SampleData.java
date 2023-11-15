package com.storeservice;

import com.storeservice.dto.StockDto;
import com.storeservice.dto.StoreDto;
import com.storeservice.mapper.StockMapper;
import com.storeservice.mapper.StoreMapper;
import com.storeservice.reopsitorys.StockRepository;
import com.storeservice.reopsitorys.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleData {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private StockMapper stockMapper;

    @Bean
    public CommandLineRunner initializeSampleDataRunner() {
        return args -> initializeSampleData();
    }

    public void initializeSampleData() {
        StoreDto store1 = StoreDto.builder()
                .name("Store-1")
                .location("location 1")
                .phone("111-111")
                .build();
        storeRepository.save(storeMapper.toEntity(store1));

        StoreDto store2 = StoreDto.builder()
                .name("store-2")
                .location("location2")
                .phone("222-222")
                .build();
        storeRepository.save(storeMapper.toEntity(store2));

        StoreDto store3 = StoreDto.builder()
                .name("store-3")
                .location("location2")
                .phone("333-333")
                .build();
        storeRepository.save(storeMapper.toEntity(store3));

        StockDto stock1 = StockDto.builder()
                .productCode("ABC123")
                .productName("Product 1")
                .quantity(50)
                .storeId(1L)
                .build();
        stockRepository.save(stockMapper.toEntity(stock1));

        StockDto stock2 = StockDto.builder()
                .productCode("XYZ789")
                .productName("Product 2")
                .quantity(30)
                .storeId(1L)
                .build();
        stockRepository.save(stockMapper.toEntity(stock2));

        StockDto stock3 = StockDto.builder()
                .productCode("DEF456")
                .productName("Product 3")
                .quantity(40)
                .storeId(2L)
                .build();
        stockRepository.save(stockMapper.toEntity(stock3));
    }
}
