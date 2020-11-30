package com.ecommerce.service.card;

import com.ecommerce.data.exceptions.CardException;
import com.ecommerce.data.exceptions.OrderException;
import com.ecommerce.data.model.Card;
import com.ecommerce.data.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository CardRepository;


    @Override
    public Card saveCard(Card card)  {
        return CardRepository.save(card);
    }

    @Override
    public Card findCardById(Integer id) {
        return CardRepository.findById(id).get();
    }

    @Override
    public void deleteCardById(Integer id) {
        CardRepository.deleteById(id);
    }

    @Override
    public List<Card> findAllCards() {
        return CardRepository.findAll();
    }

    @Override
    public Card updateCard(Card card)  {
        return CardRepository.save(card);
    }
}
