package com.ecommerce.data.repository;

import com.ecommerce.data.model.Address;
import com.ecommerce.data.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
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

    @Test
    void testThatWeCanFindAProductById(){
        product = productRepository.findById(1).orElse(null);
        assertThat(product.getId()).isNotNull();
        log.info("Product --> {}", product);
    }

    @Test
    void testThatWeCanUpdateAProduct(){
        product = productRepository.findById(2).orElse(null);

        product.setExpDate("12-04-2023");
        productRepository.save(product);

        assertThat(product.getExpDate()).isEqualTo("12-04-2023");
        log.info("updated product expiry date --> {}", product.getExpDate());
    }

    @Test
    void testThatWeCanFindAllProducts(){
        List<Product> products = productRepository.findAll();
        assertThat(products.size()).isNotZero();
        log.info("all products --> {}", products);
    }

    @Test
    void testThatWeCanDeleteProductById(){
        assertThat(productRepository.existsById(2)).isTrue();
        productRepository.deleteById(2);
        assertThat(productRepository.existsById(2)).isFalse();
    }

    @Test
    void testThatWeCanDeleteAllProducts(){
        List<Product> products = productRepository.findAll();
        productRepository.deleteAll(products);
        log.info("product --> {}", products);

    }
}