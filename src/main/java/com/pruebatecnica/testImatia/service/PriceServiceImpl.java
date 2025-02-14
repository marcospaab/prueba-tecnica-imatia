package com.pruebatecnica.testImatia.service;

import com.pruebatecnica.testImatia.entity.Price;
import com.pruebatecnica.testImatia.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class PriceServiceImpl implements PriceService{

    @Autowired
    PriceRepository priceRepository;

    @Override
    public List<Price> identificarProducto() {
        return priceRepository.findAll();
    }

    @Override
    public Price savePrice(Price price) {
        return priceRepository.save(price);
    }

    @Override
    public Price updatePrice(Long id, Price price) {
        Price priceDb = priceRepository.findById(id).get();
        if(Objects.nonNull(price.getBrandId())
            && !"".equalsIgnoreCase(String.valueOf(price.getBrandId()))){
            priceDb.setBrandId(price.getBrandId());
        }

        if(Objects.nonNull(price.getStartDate())
                && !"".equalsIgnoreCase(String.valueOf(price.getStartDate()))){
            priceDb.setStartDate(price.getStartDate());
        }

        if(Objects.nonNull(price.getEndDate())
                && !"".equalsIgnoreCase(String.valueOf(price.getEndDate()))){
            priceDb.setEndDate(price.getEndDate());
        }

        if(Objects.nonNull(price.getPriceList())
                && !"".equalsIgnoreCase(String.valueOf(price.getPriceList()))){
            priceDb.setPriceList(price.getPriceList());
        }

        if(Objects.nonNull(price.getProductId())
                && !"".equalsIgnoreCase(String.valueOf(price.getProductId()))){
            priceDb.setProductId(price.getProductId());
        }

        if(Objects.nonNull(price.getPriority())
                && !"".equalsIgnoreCase(String.valueOf(price.getPriority()))){
            priceDb.setPriority(price.getPriority());
        }

        if(Objects.nonNull(price.getPrice())
                && !"".equalsIgnoreCase(String.valueOf(price.getPrice()))){
            priceDb.setPrice(price.getPrice());
        }

        if(Objects.nonNull(price.getCurr())
                && !"".equalsIgnoreCase(String.valueOf(price.getCurr()))){
            priceDb.setCurr(price.getCurr());
        }

        return priceRepository.save(priceDb);
    }

    @Override
    public void deletePrice(Long priceList) {
        priceRepository.deleteById(priceList);
    }

    @Override
    public List<Price> getApplicablePrices(LocalDateTime date, int productId, int brandId) {
        return priceRepository.findApplicablePrices(date, productId, brandId);
    }


}
