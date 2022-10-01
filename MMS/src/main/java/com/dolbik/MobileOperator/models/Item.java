package com.dolbik.MobileOperator.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Item")
public class Item {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;
    @Column(name="category")
    private String category;
    @Column(name="parameter")
    private String parameter;
    @Column(name="weight")
    private Double weight;
    @Column(name="volume")
    private Double volume;
    @Column(name="quantity")
    private int quantity;

    @ManyToMany(mappedBy = "items")
    private List<Order> orders;
}
