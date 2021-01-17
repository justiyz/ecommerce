package com.ecommerce.data.repository;

import com.ecommerce.data.exceptions.ProductException;
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
@Sql(scripts = "classpath:db/insert.sql")
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
    }

    @Test
    void testThatWeCanSaveAProduct ()  {
        product.setName("Garlic");
        product.setPrice(200.00);
        product.setDescription("Garlic is sweet");
        product.setExpDate("12-10-12");
        product.setQuantity(10);
        try {
            productRepository.saveProduct(product);
        } catch (ProductException exp){
            log.info(exp.getMessage());
        }
        assertThat(product.getId()).isNotNull();
       log.info("product after saving --> {}", product);
    }

    @Test
    void testThatWeCannotSaveAProductWithoutProductName()  {
        product.setPrice(500.00);
        product.setDescription("Garlic is good");
        product.setExpDate("12-10-22");
        product.setQuantity(100);
        try {
            productRepository.saveProduct(product);
        } catch (ProductException exp){
            log.info(exp.getMessage());
        }
        assertThat(product.getId()).isNull();
        log.info("product after saving --> {}", product);
    }

    @Test
    void testThatWeCanSaveAProductWithoutProductPrice(){
        product.setName("Irish Potato");
        product.setDescription("Iriiiiiiiisssssh");
        product.setExpDate("12-10-22");
        product.setQuantity(20);
        try {
            productRepository.saveProduct(product);
        } catch (ProductException exp){
            log.info(exp.getMessage());
        }
        assertThat(product.getId()).isNull();
        log.info("product after saving --> {}", product);
    }

    @Test
    void testThatWeCanFindProductById(){

        product = productRepository.findById(1).get();

        assertNotNull(product);
        log.info("product by id --> {}", product);
    }

    @Test
    void testThatWeCanFindAllProducts(){
        List<Product> productList = productRepository.findAll();
        assertThat(productList).isNotNull();
        log.info("list of products --> {}", productList);
    }

    @Test
    void testThatWeCanDeleteProductById(){

        productRepository.deleteById(2);
        assertThat(productRepository.existsById(2)).isFalse();
    }



}