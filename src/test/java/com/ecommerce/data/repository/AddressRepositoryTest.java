package com.ecommerce.data.repository;

import com.ecommerce.data.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class AddressRepositoryTest {

    @Autowired
    AddressRepository addressRepository;

    Address address;

    @BeforeEach
    void setUp() {
        address = new Address();
    }

    @Test
    void testThatWeCanSaveAnAddress () {
        address.setState("Lagos");
        address.setCity("Yaba");
        address.setCountry("Nigeria");
        address.setStreet("312 Herbert Macaulay way, Sabo");
        address.setZipcode("1100110");

        log.info("address before saving -> {}", address);

        addressRepository.save(address);
        assertThat(address.getId()).isNotNull();

        log.info("address after saving -> {}", address);
    }

    @Test
    void testThatWeCanUpdateAddress () {
        address = addressRepository.findById(1).orElse(null);
        address.setZipcode("100110");
        addressRepository.save(address);

        assertThat(address.getZipcode()).isEqualTo("100110");
        log.info("Address -> {}", address);
    }

    @Test
    void testThatWeCanDeleteAddress () {
        assertThat(addressRepository.existsById(1)).isTrue();
        addressRepository.deleteById(1);
        assertThat(addressRepository.existsById(1)).isFalse();
    }

    @Test
    void testThatWeGetAllAddresses () {
        List<Address> addresses = addressRepository.findAll();
        assertThat(addresses).isNotEmpty();
    }

}