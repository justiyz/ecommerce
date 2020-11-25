package com.ecommerce.data.repository;

import com.ecommerce.data.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
    }

    @Test
    void testThatWeCanSaveAProduct(){
        product.setName("Garlic");
        product.setPrice(200.00);
        product.setDescription("Garlic is sweet");
        product.setExpDate(null);
        product.setQuantity(10);

        productRepository.save(product);
        assertThat(product.getId()).isNotNull();
    }
}