package com.ecommerce.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Data

@Setter
@Getter
@NoArgsConstructor
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

    @ManyToMany( cascade = {CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)

//    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    @JsonIgnore
    private Set<Card> cards;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    public void setAddresses (Address address) {
        if (addresses == null) {
            addresses = new ArrayList<>();
        }
        addresses.add(address);
    }

    public void setCards (Card card) {
        if (cards == null) {
            cards = new HashSet<>();
        }
        cards.add(card);
    }

}
