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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class OrderRepositoryTest {

    Order order;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testThatACustomerCanPlaceAnOrder() {
        Optional<Customer> optionalCustomer = customerRepository.findById(1);
        Customer customer = optionalCustomer.get();
        assertNotNull(customer.getId());

        List<Product> products = productRepository.findAll();
        order.setDate("20-10-20");
        order.setDelivered(true);
        order.setCanceled(false);
        order.setCustomer(customer);
        order.setProducts(products);
        assertDoesNotThrow(() -> orderRepository.saveOrder(order));
    }

    @Test
    void testSaveOrderWithoutCustomer() {

        List<Product> products = productRepository.findAll();

        order.setDate("20-10-20");
        order.setDelivered(false);
        order.setCanceled(false);
        order.setProducts(products);

        assertThrows(OrderException.class, () -> {
            orderRepository.saveOrder(order);
        });
        log.info("order --> {}", order);

    }

    @Test
    void testSaveOrderWithoutOrder() {
        Order fakeOrder = new Order();

        assertThrows(OrderException.class, () -> {
            orderRepository.saveOrder(fakeOrder);
        });

    }

    @Test
    void testSaveOrderWithoutProduct() {
        Optional<Customer> optionalCustomer = customerRepository.findById(1);
        Customer customer = optionalCustomer.get();
        assertNotNull(customer.getId());

        order.setDate("20-10-20");
        order.setDelivered(false);
        order.setCanceled(false);
        order.setCustomer(customer);

        assertThrows(OrderException.class, () -> {
            orderRepository.saveOrder(order);
        });
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testFindAllOrdersOfACustomer(){
        Optional<Customer> optionalCustomer = customerRepository.findById(1);
        Customer customer = optionalCustomer.get();
        assertNotNull(customer.getId());

        customer.getOrders();
        }

    @Test
    void testThatWeCanFindAnOrderById(){
        Optional<Order> optionalOrder = orderRepository.findById(1);
        Order order = optionalOrder.get();
        assertThat(order).isNotNull();
        log.info("order -> {}", order);
        }

    @Test
    void testThatWeCanFindAllOrders(){
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).isNotEmpty();
        log.info("orderList -> {}", orderList);
        }

    @Test
    @Transactional
    @Rollback(value = false)
    void testThatWeCanFindAllOrdersOfACustomer(){
        Optional<Customer> optionalCustomer = customerRepository.findById(2);
        Customer customer = optionalCustomer.get();
        assertNotNull(customer);

        for (Order order : customer.getOrders() ){
            log.info("customers orders --> {}", order.getDate());
        }
        assertThat(customer.getOrders()).size().isEqualTo(1);
    }

    @Test
    void testThatWeCanDeleteAnOrder(){
        assertThat(orderRepository.existsById(2)).isTrue();
        orderRepository.deleteById(2);
        assertThat(orderRepository.existsById(2)).isFalse();


    }
}