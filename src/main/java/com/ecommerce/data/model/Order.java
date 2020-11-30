package com.ecommerce.data.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "order_tb")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @ToString.Exclude
    private Customer customer;

    private String date;
    private boolean delivered;
    private boolean canceled;


    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;
}
