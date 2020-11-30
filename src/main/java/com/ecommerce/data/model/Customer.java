package com.ecommerce.data.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String password;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Address> addresses;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private Set<Card> cards;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    public void setAddresses (Address address) {
        if (addresses == null) {
            addresses = new HashSet<>();
        }
        addresses.add(address);
    }

    public void setCards (Card card) {
        if (cards == null) {
            cards = new HashSet<>();
        }
        cards.add(card);
    }


    public void getOrders(Order order) {
        if (orders == null){
            orders = new HashSet<>();
        }
        orders.add(order);
    }

}
