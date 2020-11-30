package com.ecommerce.data.repository;

import com.ecommerce.data.exceptions.CardException;
import com.ecommerce.data.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {

    public default Card saveCard (Card card) throws CardException {
        if (isValid(card)){
            save(card);
        }
        return card;
    }

    private Boolean isValid(Card card) throws CardException {
        if (!cardHasCustomer(card)) {
            throw new CardException("Can not save card without customer");
        }  if (!cardHasCardName(card)) {
            throw new CardException("Card must have a card name");
        }  if (!cardHasCvv(card)) {
            throw new CardException("Card must have cvv");
        }  if (!cardHasType(card)) {
            throw new CardException("Card type not specified");
        }  if (!cardHasExpDate(card)) {
            throw new CardException("Card expiry date not set");
        }
        return true;
    }

    private boolean cardHasCustomer (Card card) {
        return card.getCustomer() != null;
    }

    private boolean cardHasExpDate (Card card) {
        return card.getExpDate() != null;
    }

    private boolean cardHasCvv (Card card) {
        return card.getCvv() != null;
    }

    private boolean cardHasCardName (Card card) {
        return card.getCardName() != null;
    }

    private boolean cardHasType (Card card) {
        return card.getCardType() != null;
    }
}
