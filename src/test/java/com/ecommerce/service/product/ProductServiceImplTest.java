package com.ecommerce.service.product;

import com.ecommerce.data.model.Product;
import com.ecommerce.data.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService = new ProductServiceImpl();

    Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
    }

    @Test
    void testThatWeCanCAllTheSaveProductRepository(){
        when(productRepository.save(product)).thenReturn(product);
        productService.saveProduct(product);
        verify(productRepository, times(1)).save(product);
    }





}