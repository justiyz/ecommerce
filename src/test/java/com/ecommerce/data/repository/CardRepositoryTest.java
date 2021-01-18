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

import java.util.List;
import java.util.Optional;

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

        log.info("card -> {}", card);
        assertDoesNotThrow( () -> {
            cardRepository.saveCard(card);
        });
    }

    @Test
    void testSaveCardWithoutCustomer () {
        card.setCardName("Fola Femi");
        card.setCardNumber("201002333444");
        card.setCardType("MasterCard");
        card.setCvv(230);
        card.setExpDate("1-10-24");

        assertThrows(CardException.class, ()-> {
            cardRepository.saveCard(card);
        });
    }

    @Test
    void testThatWeCanFindACardById(){
        Optional<Card> optionalCard = cardRepository.findById(2);
        Card card = optionalCard.get();
        assertThat(card.getId()).isNotNull();
        log.info("card found --> {}", card.getCardName());
    }

    @Test
    void testThatWeCanFindAllCards(){
        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).isNotNull();
        log.info("card list --> {}", cardList);
    }

    @Test
    void testThatWeCanUpdateCard(){
        Optional<Customer> optionalCustomer = customerRepository.findById(2);
        Customer customer = optionalCustomer.get();
        assertThat(customer).isNotNull();

        Optional<Card> optionalCard = cardRepository.findById(2);
        Card card = optionalCard.get();
        card.setCardName("Fabinho Tavares");
        card.setCustomer(customer);

        cardRepository.save(card);
    }

    @Test
    void testThatWeCanDeleteCard(){
        assertThat(cardRepository.existsById(2)).isTrue();
        cardRepository.deleteById(2);
        assertThat(cardRepository.existsById(2)).isFalse();
    }



}