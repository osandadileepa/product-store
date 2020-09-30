package com.osanda.test.productStore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.osanda.test.productStore.dto.PriceCalculateDto;
import com.osanda.test.productStore.exception.ProductNotFoundException;
import com.osanda.test.productStore.service.PriceCalculateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${spring.data.rest.base-path}/price/")
public class PriceController {

	private final PriceCalculateService priceCalculateService;

	/***
	 * @author Osanda Wedamulla
	 * 
	 * @param id
	 * @param units
	 * @return ResponseEntity
	 */
	@GetMapping("{id}")
	public ResponseEntity<?> calculatePriceFromProductIdAndTotalUnits(@PathVariable Long id,
			@RequestParam(name = "unit") Integer units) {

		try {

			PriceCalculateDto priceDto = this.priceCalculateService.calculateProductPriceFromUnits(id, units);

			return ResponseEntity.ok(priceDto);

		} catch (ProductNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product does not exits !!!");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product Price Calculation Error !!!");
		}

	}// calculatePriceFromProductIdAndTotalUnits()
	
	/***
	 * @author Osanda Wedamulla
	 * 
	 * @param productId
	 * @param unitRange
	 * @return ResponseEntity
	 */
	@GetMapping("list")
	public ResponseEntity<?> getPriceListCalculated(@RequestParam(name = "productId") Long productId,
			@RequestParam(name = "unit") Integer unitRange) {

		try {

			List<PriceCalculateDto> priceListForRange = this.priceCalculateService.getPriceListForRange(productId,
					unitRange);

			return ResponseEntity.ok(priceListForRange);

		} catch (ProductNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product does not exits !!!");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product Price Calculation Error !!!");
		}

	}// getPriceListCalculated()

}// PriceController()
