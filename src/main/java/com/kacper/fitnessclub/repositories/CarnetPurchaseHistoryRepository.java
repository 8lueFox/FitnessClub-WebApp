package com.kacper.fitnessclub.repositories;

import com.kacper.fitnessclub.models.Carnet;
import com.kacper.fitnessclub.models.CarnetPurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarnetPurchaseHistoryRepository extends JpaRepository<CarnetPurchaseHistory, Integer> {

    @Query("SELECT c.carnet.id FROM CarnetPurchaseHistory c WHERE :user = c.user.id")
    Integer findCarnetBoughtByUser(@Param("user") Integer userId);

}
