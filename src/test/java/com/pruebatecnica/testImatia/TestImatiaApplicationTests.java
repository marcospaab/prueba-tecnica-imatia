package com.pruebatecnica.testImatia;

import com.pruebatecnica.testImatia.controller.PriceController;
import com.pruebatecnica.testImatia.entity.Price;
import com.pruebatecnica.testImatia.repository.PriceRepository;
import com.pruebatecnica.testImatia.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceServiceApplicationTests {

	@Autowired
	private PriceRepository priceRepository;

	@InjectMocks
	private PriceController priceController;

	@Mock
	private PriceService priceService;

	private final DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	private final DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

	@BeforeEach
	void init() {
		priceService = mock(PriceService.class);
		priceController = new PriceController(priceService);
	}

	private LocalDateTime parseFlexibleDate(String dateStr) {
		try {
			return LocalDateTime.parse(dateStr, dateTimeFormatter1);
		} catch (Exception e) {
			return LocalDateTime.parse(dateStr, dateTimeFormatter2);
		}
	}

	@Test
	void case1() {
		testPriceQuery("2020-06-14T10:00:00", 1, 35455, 1, "2020-06-14-00.00.00",
				"2020-12-31-23.59.59", 35.50, "EUR");
	}

	@Test
	void cas2() {
		testPriceQuery("2020-06-14T16:00:00", 1, 35455, 2, "2020-06-14-15.00.00",
				"2020-06-14-18.30.00", 25.45, "EUR");
	}

	@Test
	void case3() {
		testPriceQuery("2020-06-14T21:00:00", 1, 35455, 1, "2020-06-14-00.00.00",
				"2020-12-31-23.59.59", 35.50, "EUR");
	}

	@Test
	void case4() {
		testPriceQuery("2020-06-15T10:00:00", 1, 35455, 3, "2020-06-15T00:00:00",
				"2020-06-15-11.00.00", 30.50, "EUR");
	}

	@Test
	void case5() {
		testPriceQuery("2020-06-16T21:00:00", 1, 35455, 4, "2020-06-16-16.00.00",
				"2020-12-31-23.59.59", 38.95, "EUR");
	}

	private void testPriceQuery(String dateStr, int brandId, int productId, int priceList, String startDate, String endDate,
								double expectedPrice, String curr) {

		LocalDateTime date = parseFlexibleDate(dateStr);
		Price price = new Price();
		price.setBrandId(brandId);
		price.setProductId(productId);
		price.setPriceList((long) priceList);
		price.setStartDate(parseFlexibleDate(startDate));
		price.setEndDate(parseFlexibleDate(endDate));
		price.setPrice(expectedPrice);
		price.setCurr(curr);
		when(priceService.getApplicablePrices(eq(date), eq(productId), eq(brandId))).thenReturn(List.of(price));
		ResponseEntity<List<Price>> response = priceController.getApplicablePrices(dateStr, productId, brandId);
		assertNotNull(response, "La respuesta no debe ser nula.");
		assertEquals(200, response.getStatusCodeValue(), "El código de estado debe ser 200");
		assertNotNull(response.getBody(), "El cuerpo de la respuesta no debe ser nulo.");
		assertEquals(1, response.getBody().size(), "El tamaño de la respuesta debe ser 1.");
		assertEquals(expectedPrice, response.getBody().get(0).getPrice(), 0.01, "El precio debe ser el esperado.");
	}

}
