package com.ecommerce.data.repository;

import com.ecommerce.data.model.Address;
import com.ecommerce.data.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Sql(scripts = "classpath:db/insert.sql")
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

        Address address = addressRepository.findById(1).orElse(null);
        customer.setAddresses(address);

        assertDoesNotThrow(() -> customerRepository.saveCustomer(customer));
    }

    @Test
    void testThatTwoCustomersCanShareOneAddress () {
       customer = customerRepository.findById(2).orElse(null);

        Address address = addressRepository.findById(1).orElse(null);
        customer.setAddresses(address);

        assertDoesNotThrow(() -> customerRepository.saveCustomer(customer));
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testThatOneCustomerCanHaveMultipleAddresses () {
        customer = customerRepository.findById(2).orElse(null);

        Address address = addressRepository.findById(1).orElse(null);

        customer.setAddresses(address);

        assertDoesNotThrow(() -> customerRepository.saveCustomer(customer));
        assertThat(customer.getAddresses().size()).isEqualTo(2);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testThatWeCanFetchAllCustomerAddress () {
        customer = customerRepository.findById(1).orElse(null);

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
        customer =  customerRepository.findById(1).orElse(null);

        assert customer != null;

        Address address = addressRepository.findById(1).orElse(null);

        if (customer.getAddresses().contains(address)) {
            customer.getAddresses().remove(address);
        }

        assertThat(customer.getAddresses().size()).isEqualTo(1);
    }

    @Test
    void testThatWeCanUpdateCustomerDetails () {
        customer = customerRepository.findById(2).orElse(null);
        customer.setPassword("tobi123");

        assertDoesNotThrow(() -> customerRepository.saveCustomer(customer));
        assertThat(customer.getPassword()).isEqualTo("tobi123");
    }

}









