package com.storeservice.reopsitorys;

import com.storeservice.models.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {

}
