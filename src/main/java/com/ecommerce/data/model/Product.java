package com.ecommerce.data.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String expDate;
    private Double price;
    private Integer quantity;
    private String serialNumber;
    private String weight;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

}
