package com.pruebatecnica.testImatia.controller;

import com.pruebatecnica.testImatia.entity.Price;
import com.pruebatecnica.testImatia.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class PriceController {

    @Autowired
    PriceService priceService;


    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/identificarProducto")
    public List<Price> identificarProducto(){
        return priceService.identificarProducto();
    }



    @PostMapping("/savePrice")
    public Price savePrice(@RequestBody Price price){
        return priceService.savePrice(price);
    }

    @PutMapping("/updatePrice/{priceList}")
    public Price updatePrice(@PathVariable Long priceList, @RequestBody Price price){
        return priceService.updatePrice(priceList, price);
    }

    @DeleteMapping("/deletePrice/{priceList}")
    public String deletePrice(@PathVariable Long priceList){
        priceService.deletePrice(priceList);
        return "El registro con id " + priceList + " ha sido eliminado.";
    }

    @GetMapping("/getApplicablePrices")
    public ResponseEntity<List<Price>> getApplicablePrices(
            @RequestParam String date,
            @RequestParam int productId,
            @RequestParam int brandId) {

        LocalDateTime parsedDate = parseDate(date);
        List<Price> applicablePrices = priceService.getApplicablePrices(parsedDate, productId, brandId);

        if (applicablePrices.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(applicablePrices);
        }
    }


    private LocalDateTime parseDate(String dateStr) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

        try {
            return LocalDateTime.parse(dateStr, formatter1);
        } catch (Exception e) {
            return LocalDateTime.parse(dateStr, formatter2);
        }
    }

}
