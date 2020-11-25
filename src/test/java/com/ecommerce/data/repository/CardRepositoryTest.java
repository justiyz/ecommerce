package com.ecommerce.data.repository;

import com.ecommerce.data.model.Card;
import com.ecommerce.data.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class CardRepositoryTest {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;

    Card card;

    @BeforeEach
    void setUp() {
        card = new Card();

    }

    @Test
    void testThatWeCanSaveCard(){
        card.setCardName("John Bull");
        card.setCardNumber("098709854");
        card.setCardType("Verve");
        card.setCvv(555-666-000-22);
        card.setExpDate("12-12-2020");

        Customer customer = customerRepository.findById(1).orElse(null);

        assert customer != null;
        card.setCustomer(customer);
        cardRepository.save(card);
        assertThat(card.getId()).isNotNull();

        log.info("card -> {}", card);
    }

    @Test
    void testThatACustomerCanHaveMultipleCards(){
        card = cardRepository.findById(2).orElse(null);
        assert card != null;

        Customer customer = customerRepository.findById(1).orElse(null);
        assert customer != null;

        card.setCustomer(customer);
        cardRepository.save(card);

        assertThat(card.getId()).isNotNull();
        assertThat(card.getCustomer()).isNotNull();

        log.info("card --> {}", card);
    }

}