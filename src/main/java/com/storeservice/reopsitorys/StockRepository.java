package com.storeservice.reopsitorys;

import com.storeservice.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT s FROM Stock s WHERE s.productCode = :productCode")
    Optional<Stock> findByProductCode(@Param("productCode") String productCode);
}
