package com.ecommerce.data.repository;

import com.ecommerce.data.model.Address;
import com.ecommerce.data.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.Access;
import javax.security.auth.login.AccountException;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
    }

    @Test
    void testThatWeCanSaveCustomer () {
        customer.setContact("09031861100");
        customer.setEmail("iclasschima@gmail.com");
        customer.setFirstName("iClass");
        customer.setLastName("Chima");
        customer.setPassword("iclass123");

        Address address = new Address();

        address.setZipcode("001001");
        address.setStreet("Folarin street");
        address.setCountry("Nigeria");
        address.setCity("Lagos");
        address.setState("Lagos");

        customer.setAddresses(address);

        log.info("customer before saving -> {}", customer);

        customerRepository.save(customer);
        assertThat(customer.getId()).isNotNull();

        log.info("customer after saving -> {}", customer);

    }

    @Test
    void testThatTwoCustomersCanShareOneAddress () {
        customer.setPassword("iclass123");
        customer.setLastName("Tobi");
        customer.setFirstName("Femi");
        customer.setEmail("tobifemi@gmail.com");
        customer.setContact("09023237811");

        Address address = addressRepository.findById(4).orElse(null);

        customer.setAddresses(address);

        customerRepository.save(customer);

        log.info("customer -> {}", customer);

        assertThat(customer.getId()).isNotNull();
        assertThat(customer.getAddresses()).isNotEmpty();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testThatOneCustomerCanHaveMultipleAddresses () {
        customer = customerRepository.findById(2).orElse(null);

        Address address = addressRepository.findById(3).orElse(null);

        customer.setAddresses(address);

        customerRepository.save(customer);

        log.info("customer -> {}", customer);

        assertThat(customer.getAddresses().size()).isEqualTo(2);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testThatWeCanFetchAllCustomerAddress () {
        customer = customerRepository.findById(2).orElse(null);

        assert customer != null;
        for (Address address : customer.getAddresses()) {
           log.info("address -> {}", address.getStreet());
       }

       assertThat(customer.getAddresses().size()).isEqualTo(2);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testThatWeCanRemoveAnAddressFromACustomerAddressesList () {
        customer =  customerRepository.findById(2).orElse(null);

        assert customer != null;

        Address address = addressRepository.findById(3).orElse(null);

        if (customer.getAddresses().contains(address)) {
            customer.getAddresses().remove(address);
        }

        assertThat(customer.getAddresses().size()).isEqualTo(1);
    }

}









