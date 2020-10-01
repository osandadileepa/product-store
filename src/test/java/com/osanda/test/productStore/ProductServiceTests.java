package com.osanda.test.productStore;

import com.osanda.test.productStore.model.Product;
import com.osanda.test.productStore.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class ProductServiceTests {

    @Autowired
    private ProductService productService;

    List<Product> productList = new ArrayList<>();
    Product product;

    @BeforeEach
    public void initial() {
        this.productList.add(new Product(1L, "Penguin-ears", 20, 175.0, "Penguin ear product for your reference"));
        this.productList.add(new Product(2L, "Horseshoe", 5, 825.0, "Horseshow product for your reference"));
    }

    @Test
    public void getAllAvailableProductsTest() {

        List<Product> allProducts = this.productService.getAllProducts();

        assertThat(allProducts).isNotEmpty();
        assertThat(allProducts).isNotNull();

        assertThat(allProducts.size()).isEqualTo(this.productList.size());

    }// getAllAvailableProductsTest()

    @Test
    public void saveProductTest() {
        this.product = new Product(3L, "Tiger-Item", 2, 15.0, "Tiger product for your reference");

        Product savedProduct = this.productService.addProduct(this.product);

        assertThat(savedProduct.getName()).isEqualTo(this.product.getName());
        assertThat(savedProduct.getUnitsPerCarton()).isEqualTo(this.product.getUnitsPerCarton());
        assertThat(savedProduct.getCartonPrice()).isEqualTo(this.product.getCartonPrice());
        assertThat(savedProduct.getDescription()).isEqualTo(this.product.getDescription());
    }// saveProductTest()

}
