package com.ecommerce.service.card;

import com.ecommerce.data.exceptions.CardException;
import com.ecommerce.data.model.Card;
import com.ecommerce.data.model.Product;
import com.ecommerce.data.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CardServiceImplTest {

    @Mock
    CardRepository cardRepository;

    @InjectMocks
    CardService cardService = new CardServiceImpl();

    Card card;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        card = new Card();
    }

    @Test
    void mockTheSaveCardServiceTest() throws CardException {
        when(cardRepository.save(card)).thenReturn(card);
        cardService.saveCard(card);
        verify(cardRepository, times(1)).save(card);
    }


    @Test
    void mockTheFindCardByIdServiceTest(){
        when(cardRepository.findById(2)).thenReturn(Optional.of(card));
        cardService.findCardById(2);
        verify(cardRepository, times(1)).findById(2);
    }

    @Test
    void mockTheDeleteByIdServiceTest(){
        doNothing().when(cardRepository).deleteById(1);
        cardService.deleteCardById(1);
        verify(cardRepository, times(1)).deleteById(1);
    }

    @Test
    void mockTheFindAllCardsServiceTest(){
        when(cardRepository.findAll()).thenReturn(List.of(card));
        cardService.findAllCards();
        verify(cardRepository, times(1)).findAll();
    }

    @Test
    void mockTheUpdateCardServiceRepository(){
        when(cardRepository.save(card)).thenReturn(card);
        cardService.updateCard(card);
        verify(cardRepository, times(1)).save(card);
    }















}