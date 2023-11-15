package com.storeservice.reopsitorys;

import com.storeservice.dto.StoreDto;
import com.storeservice.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByName(String name);
}
