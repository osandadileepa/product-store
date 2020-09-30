package com.osanda.test.productStore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.osanda.test.productStore.model.Product;
import com.osanda.test.productStore.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	/***
	 * @author Osanda Wedamulla
	 * @return Product
	 */
	public Product addProduct(Product product) {

		Product save = this.productRepository.save(product);
		log.info("New Product saved : " + save.getId());

		return save;

	}// addProduct()

	/***
	 * @author Osanda Wedamulla
	 * @return List<Product>
	 */
	public List<Product> getAllProducts() {

		return this.productRepository.findAll();
	}// getAllProducts()

}// ProductService()
