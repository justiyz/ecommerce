package com.ecommerce.data.repository;

import com.ecommerce.data.exceptions.OrderException;
import com.ecommerce.data.model.Customer;
import com.ecommerce.data.model.Order;
import com.ecommerce.data.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    Order order;

    @BeforeEach
    void setUp() {
      order = new Order();
    }

    @Test
    void testThatACustomerCanPlaceAnOrder(){
        Optional<Customer> optionalCustomer = customerRepository.findById(1);
        Customer customer = optionalCustomer.get();
        assertNotNull(customer.getId());

        List<Product> product = productRepository.findAll();

        order.setDate("12-05-2021");
        order.setDelivered(false);
        order.setCustomer(customer);
        order.setCancelled(false);
        order.setProducts(product);

        orderRepository.save(order);

        assertNotNull(order);
        assertNotNull(order.getId());
    }

    @Test
    void testThatWeCanSaveOrderWithoutCustomer(){
        List<Product> product = productRepository.findAll();

        order.setDate("04-07-2021");
        order.setDelivered(false);
        order.setCancelled(false);
        order.setProducts(product);

        assertThrows(OrderException.class, () -> {
            orderRepository.saveOrder(order);
        });
    }

    @Test
    void testSaveOrderWithoutAnOrder(){
        Order fakeOrder = new Order();

        assertThrows(OrderException.class, () -> {
            orderRepository.saveOrder(fakeOrder);
        });
        assertNull(fakeOrder.getId());
    }

    @Test
    void testSaveOrderWithoutProduct(){
        Optional<Customer> optionalCustomer = customerRepository.findById(1);
        Customer customer = optionalCustomer.get();
        assertNotNull(customer.getId());

        List<Product> product = productRepository.findAll();

        order.setDate("12-05-2021");
        order.setDelivered(false);
        order.setCustomer(customer);
        order.setCancelled(false);

        //we use lambda here instead of the try/catch which is a bad programming practice
        assertThrows(OrderException.class, () -> {
            orderRepository.saveOrder(order);
        });
        assertNull(order.getId());
    }




}

