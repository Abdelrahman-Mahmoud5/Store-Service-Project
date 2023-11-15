package com.storeservice.mapper;

import com.storeservice.dto.StockDto;
import com.storeservice.dto.StoreDto;
import com.storeservice.models.Stock;
import com.storeservice.models.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StockMapper {
    @Mapping(target = "store.id", source = "storeId")
    Stock toEntity(StockDto stockDto);

    @Mapping(target = "storeId", source = "store.id")
    StockDto toDto(Stock stock);
}
