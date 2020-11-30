package com.ecommerce.data.repository;

import com.ecommerce.data.exceptions.CardException;
import com.ecommerce.data.exceptions.CustomerException;
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
@Sql(scripts = "classpath:db/insert.sql")
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
    void testThatWeCanSaveCard () {
        card.setCardName("Fola Ope");
        card.setCardNumber("201002333444");
        card.setCardType("MasterCard");
        card.setCvv(230);
        card.setExpDate("1-10-24");

        Customer customer = customerRepository.findById(1).orElse(null);
        assert customer != null;
        card.setCustomer(customer);
        try {
            cardRepository.saveCard(card);
        }catch (CardException exp){
            log.info(exp.getMessage());
        }
        assertThat(card.getId()).isNotNull();
        log.info("card -> {}", card);
    }

    @Test
    void testSaveCardWithoutCustomer () {
        card.setCardName("Fola Femi");
        card.setCardNumber("201002333444");
        card.setCardType("MasterCard");
        card.setCvv(230);
        card.setExpDate("1-10-24");

//        try {
//            cardRepository.saveCard(card);
//        }catch (CardException exp){
//            log.info(exp.getMessage());
//        }

        assertThrows(CardException.class, ()-> {
            cardRepository.saveCard(card);
        });
    }

    @Test
    void testThatOneCustomerCanHaveMultipleCards () {
        card = cardRepository.findById(2).orElse(null);
        assert card != null;

        Customer customer = customerRepository.findById(1).orElse(null);
        assert customer != null;

        card.setCustomer(customer);
        assertDoesNotThrow(() -> {cardRepository.save(card); });
    }

}