package com.pruebatecnica.testImatia.repository;

import com.pruebatecnica.testImatia.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query("SELECT p FROM Price p WHERE p.productId = :productId AND p.brandId = :brandId " +
            "AND :date BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC")
    List<Price> findApplicablePrices(@Param("date") LocalDateTime date,
                                     @Param("productId") int productId,
                                     @Param("brandId") int brandId);

   /* @Query("SELECT p FROM Price p " +
            "WHERE p.productId = :productId " +
            "AND p.brandId = :brandId " +
            "AND :date BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC")
    List<Price> findApplicablePrices(@Param("date") LocalDateTime date,
                                     @Param("productId") int productId,
                                     @Param("brandId") int brandId);*/
}
