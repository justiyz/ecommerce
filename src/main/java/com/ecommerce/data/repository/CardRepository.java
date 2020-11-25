package com.ecommerce.data.repository;

import com.ecommerce.data.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {

}
