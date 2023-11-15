package com.storeservice.mapper;

import com.storeservice.dto.StockHistoryDto;
import com.storeservice.dto.StoreDto;
import com.storeservice.models.StockHistory;
import com.storeservice.models.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockHistoryMapper {
    @Mapping(target = "store.id", source = "storeId")
    StockHistory toEntity(StockHistoryDto stockHistoryDto);

    @Mapping(target = "storeId", source = "store.id")
    StockHistoryDto toDto(StockHistory stockHistory);

    List<StockHistoryDto> listDto(List<StockHistory> t);
}
