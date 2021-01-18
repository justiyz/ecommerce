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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class AddressRepositoryTest {

    @Autowired
    AddressRepository addressRepository;

    @Autowired CustomerRepository customerRepository;

    Address address;

    @BeforeEach
    void setUp() {
        address = new Address();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void testThatWeCanSaveAnAddress () {
        address.setState("Lagos");
        address.setCity("Ikeja");
        address.setCountry("Nigeria");
        address.setStreet("52, Iweka road, Onitsha");
        address.setZipcode("111110");

        Optional<Customer> optionalCustomer = customerRepository.findById(1);
        Customer customer = optionalCustomer.get();
        assertThat(customer).isNotNull();

        address.setCustomers(customer);
        assertThat(address.getCustomers()).isNotNull();

        log.info("customer after creating --> {}", customer);

        assertDoesNotThrow(() ->  addressRepository.saveAddress(address));
        log.info("address saved --> {}", address);
    }

    @Test
    void testThatWeCanUpdateAddress () {
        address = addressRepository.findById(1).orElse(null);
        address.setZipcode("100313");
        addressRepository.save(address);

        assertThat(address.getZipcode()).isEqualTo("100313");
    }

    @Test
    void testThatWeCanDeleteAddress () {
        assertThat(addressRepository.existsById(3)).isTrue();
        addressRepository.deleteById(3);
        assertThat(addressRepository.existsById(3)).isFalse();
    }

    @Test
    void testThatWeCanFindAddressById(){
       Optional<Address> optionalAddress = addressRepository.findById(4);
       Address address = optionalAddress.get();
       log.info("address found -> {}", address);
       assertThat(address).isNotNull();
    }

    @Test
    void testThatWeCanGetAllAddresses () {
        List<Address> addresses = addressRepository.findAll();
        assertThat(addresses).isNotEmpty();
        log.info("List of addresses --> {}", addresses);
    }


}