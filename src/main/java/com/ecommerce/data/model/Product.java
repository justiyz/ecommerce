package com.ecommerce.data.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String expDate;
    private Double price;
    private Integer quantity;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

}
