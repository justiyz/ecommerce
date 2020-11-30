package com.ecommerce.data.repository;

import com.ecommerce.data.model.Address;
import com.ecommerce.data.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
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
        address.setCity("Yaba");
        address.setCountry("Nigeria");
        address.setStreet("312 Herbert Macaulay way, Sabo");
        address.setZipcode("100110");
//      address = addressRepository.findById(2).orElse(null);

        Customer customer = customerRepository.findById(1).orElse(null);
        address.setCustomers(customer);

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
        assertThat(addressRepository.existsById(2)).isTrue();
        addressRepository.deleteById(2);
        assertThat(addressRepository.existsById(2)).isFalse();
    }

    @Test
    void testThatWeCanGetAllAddresses () {
        List<Address> addresses = addressRepository.findAll();
        assertThat(addresses).isNotEmpty();
        log.info("List of addresses --> {}", addresses);
    }


}