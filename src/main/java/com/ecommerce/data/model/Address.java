package com.ecommerce.data.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String country;
    private String street;
    private String state;
    private String city;
    private String zipcode;

}
