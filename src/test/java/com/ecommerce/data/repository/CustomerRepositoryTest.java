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

import javax.swing.text.html.Option;
import javax.transaction.Transactional;

import java.util.Optional;

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
        customer.setContact("09131863333");
        customer.setEmail("ndang@co.gov");
        customer.setFirstName("white");
        customer.setLastName("man");
        customer.setPassword("iclass123");

        Optional<Address> optionalAddress = addressRepository.findById(1);
        Address address = optionalAddress.get();
        customer.setAddresses(address);

        assertDoesNotThrow(() -> customerRepository.saveCustomer(customer));
    }

    @Test
    void testThatTwoCustomersCanShareOneAddress () {
       Customer customer1 = customerRepository.findById(2).orElse(null);
       Customer customer2 = customerRepository.findById(1).orElse(null);

        Address address = addressRepository.findById(1).orElse(null);
        customer1.setAddresses(address);
        customer2.setAddresses(address);

        assertDoesNotThrow(() -> customerRepository.saveCustomer(customer1));
        assertDoesNotThrow(() -> customerRepository.saveCustomer(customer2));
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testThatOneCustomerCanHaveMultipleAddresses () {
        customer = customerRepository.findById(2).orElse(null);

        Optional<Address> optionalAddress1 = addressRepository.findById(1);
        Address address1 = optionalAddress1.get();
        assertNotNull(address1);
        customer.setAddresses(address1);

        Optional<Address> optionalAddress2 = addressRepository.findById(4);
        Address address2 = optionalAddress2.get();
        assertNotNull(address2);
        customer.setAddresses(address2);

        assertDoesNotThrow(() -> customerRepository.saveCustomer(customer));
        assertThat(customer.getAddresses().size()).isNotNull();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testThatWeCanFetchAllCustomerAddress () {
        customer = customerRepository.findById(1).orElse(null);
        assertNotNull(customer);

        for (Address address : customer.getAddresses()) {
           log.info("address -> {}", address.getStreet());
       }
       assertThat(customer.getAddresses().size()).isEqualTo(2);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testThatWeCanRemoveAnAddressFromACustomerAddressesList () {
        Optional<Customer> optionalCustomer = customerRepository.findById(1);
        Customer customer = optionalCustomer.get();
        assertNotNull(customer);

        Optional<Address> optionalAddress = addressRepository.findById(1);
        Address address = optionalAddress.get();
        assertNotNull(address);

        if (customer.getAddresses().contains(address)) {
            customer.getAddresses().remove(address);
        }
        assertThat(customer.getAddresses().size()).isEqualTo(1);
    }

    @Test
    void testThatWeCanUpdateCustomerDetails () {
        Optional<Customer> optionalCustomer = customerRepository.findById(3);
        Customer customer = optionalCustomer.get();
        assertNotNull(customer);
        customer.setPassword("NewAnti123");

        assertDoesNotThrow(() -> customerRepository.saveCustomer(customer));
        assertThat(customer.getPassword()).isEqualTo("NewAnti123");
    }

}









