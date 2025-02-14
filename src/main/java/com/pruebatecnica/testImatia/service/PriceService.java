package com.pruebatecnica.testImatia.service;

import com.pruebatecnica.testImatia.entity.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceService {
    List<Price> identificarProducto();

    Price savePrice(Price price);

    Price updatePrice(Long priceList, Price price);

    void deletePrice(Long priceList);

    List<Price> getApplicablePrices(LocalDateTime date, int productId, int brandId);
}