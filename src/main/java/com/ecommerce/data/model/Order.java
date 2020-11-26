package com.ecommerce.data.model;

import lombok.Data;

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
    private Customer customer;

    private String date;

    private boolean delivered;

    private boolean cancelled;

    @ManyToMany()
    private List<Product> products;

}
