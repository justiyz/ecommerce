package com.ecommerce.data.model;

import lombok.*;

import javax.persistence.*;

//@Data
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cardType;
    private Integer cvv;
    private String cardNumber;
    private String cardName;
    private String expDate;

    @OneToOne()
    @ToString.Exclude
    private Customer customer;


    public void setCustomer (Customer customer) {
        if (getCustomer() == null) {
            this.customer = customer;
        }
    }

}
