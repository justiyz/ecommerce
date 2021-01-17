package com.ecommerce.data.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String country;
    private String street;
    private String state;
    private String city;
    private String zipcode;


    @ManyToMany(mappedBy = "addresses", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private Set<Customer> customers;

    public void setCustomers (Customer customer) {
        if (customers == null) {
            customers = new HashSet<>();
        }
        customers.add(customer);
    }

}
