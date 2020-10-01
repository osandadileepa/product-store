package com.osanda.test.productStore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.osanda.test.productStore.dto.PriceCalculateDto;
import com.osanda.test.productStore.exception.ProductNotFoundException;
import com.osanda.test.productStore.model.Product;
import com.osanda.test.productStore.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceCalculateService {

	private final ProductRepository productRepository;

	/***
	 * @author Osanda Wedamulla
	 * 
	 * @param productId
	 * @param units
	 * @return PriceCalculateDto
	 * @throws Exception
	 */
	public PriceCalculateDto calculateProductPriceFromUnits(Long productId, int units) throws Exception {

		PriceCalculateDto calculatePrice = new PriceCalculateDto();

		Product product = this.getProductFromDatabase(productId);
		calculatePrice.setProductId(productId);

		// calculate carton and single units and set to price model
		this.calculateNumberOfCartonsAndSingleUnits(product, units, calculatePrice);
		// calculate prices from price model details
		this.calculatePricesFromProductAndGeneratedPriceModel(product, calculatePrice);

		return calculatePrice;

	}// calculateProductPriceFromUnits()

	/***
	 * get Product existence from db
	 * 
	 * @author Osanda Wedamulla
	 * 
	 * @param productId
	 * @return Product
	 * @throws ProductNotFoundException
	 */
	private Product getProductFromDatabase(Long productId) throws Exception {

		Optional<Product> product = this.productRepository.findById(productId);

		if (product.isPresent()) {
			return product.get();
		} else {
			log.error("Product not found for id : " + productId);
			throw new ProductNotFoundException();
		}

	}// getProductFromDatabase()

	/***
	 * set number of cartons and single units
	 * 
	 * @author Osanda Wedamulla
	 * 
	 * @param product
	 * @param totalUnits
	 * @param priceCalculateDto
	 * @return PriceCalculateDto
	 */
	private PriceCalculateDto calculateNumberOfCartonsAndSingleUnits(Product product, int totalUnits,
			PriceCalculateDto priceCalculateDto) {

		int cartonAmount = totalUnits / product.getUnitsPerCarton();
		int singleAmount = totalUnits % product.getUnitsPerCarton();

		priceCalculateDto.setCartonAmount(cartonAmount);
		priceCalculateDto.setSingleUnitsAmount(singleAmount);

		return priceCalculateDto;

	}// calculateNumberOfCartonsAndSingleUnits()

	/***
	 * get the final cost form the price model
	 * 
	 * @author Osanda Wedamulla
	 * 
	 * @param product
	 * @param priceCalculateDto
	 * @return PriceCalculateDto
	 */
	private PriceCalculateDto calculatePricesFromProductAndGeneratedPriceModel(Product product,
			PriceCalculateDto priceCalculateDto) {

		int cartonAmount = priceCalculateDto.getCartonAmount();
		int singleUnitAmount = priceCalculateDto.getSingleUnitsAmount();

		double productCartonPrice = product.getCartonPrice();

		double costOfCartons = 0.0, costOfSingleUnits = 0.0;

		if (cartonAmount > 3) {
			costOfCartons = (productCartonPrice * cartonAmount) * 0.9;
		} else {
			costOfCartons = productCartonPrice * cartonAmount;
		}

		if (singleUnitAmount > 0) {
			costOfSingleUnits = ((productCartonPrice * singleUnitAmount) / product.getUnitsPerCarton()) * 1.3;
		}

		priceCalculateDto.setCostOfSingleUnits(costOfSingleUnits);
		priceCalculateDto.setCostOfCartons(costOfCartons);

		double totalCost = costOfCartons + costOfSingleUnits;

		priceCalculateDto.setTotalCost(totalCost);

		return priceCalculateDto;

	}// calculatePricesFromProductAndGeneratedPriceModel()

	/***
	 * calculate price list for range starting from 1
	 * 
	 * @author Osanda Wedamulla
	 * 
	 * @param productId
	 * @param unitRange
	 * @return List<PriceCalculateDto>
	 * @throws Exception
	 */
	public List<PriceCalculateDto> getPriceListForRange(Long productId, Integer unitRange) throws Exception {

		List<PriceCalculateDto> priceModelList = new ArrayList<>();

		Product product = this.getProductFromDatabase(productId);

		IntStream.range(1, (unitRange + 1)).forEach(i -> {

			PriceCalculateDto calculatePrice = new PriceCalculateDto();
			calculatePrice.setProductId(productId);

			// calculate carton and single units and set to price model
			this.calculateNumberOfCartonsAndSingleUnits(product, i, calculatePrice);
			// calculate prices from price model details
			this.calculatePricesFromProductAndGeneratedPriceModel(product, calculatePrice);

			priceModelList.add(calculatePrice);

		});

		return priceModelList;

	}// getPriceListForRange()

}// PriceCalculateService()