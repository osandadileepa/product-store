package com.osanda.test.productStore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osanda.test.productStore.model.Product;
import com.osanda.test.productStore.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${spring.data.rest.base-path}/products")
public class ProductController {

	private final ProductService productService;

	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product saveProduct = productService.addProduct(product);

		if (saveProduct != null) {
			return ResponseEntity.ok(saveProduct);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Product());

	}// addProduct()

	@GetMapping
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}// getProducts()

}// ProductController {}
