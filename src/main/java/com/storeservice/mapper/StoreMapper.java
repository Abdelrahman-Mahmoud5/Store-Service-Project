package com.storeservice.mapper;

import com.storeservice.dto.StoreDto;
import com.storeservice.models.Store;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    Store toEntity(StoreDto storeDtoDto);

    StoreDto toDto(Store store);

    List<StoreDto> listDto(List<Store> t);
}
