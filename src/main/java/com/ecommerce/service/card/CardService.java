package com.ecommerce.service.card;

import com.ecommerce.data.exceptions.CardException;

import com.ecommerce.data.model.Card;
import com.ecommerce.data.model.Order;

import java.util.List;


public interface CardService {

    public Card saveCard(Card card) ;
    public Card findCardById(Integer id);
    public void deleteCardById(Integer id);
    public List<Card> findAllCards();
    public Card updateCard(Card card) ;
}
