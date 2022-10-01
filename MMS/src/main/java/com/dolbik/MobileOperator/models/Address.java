package com.dolbik.MobileOperator.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Address")
@Data
@NoArgsConstructor
public class Address {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "country")
    private String country;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "postalCode")
    private int postalCode;

    @NotNull
    @Column(name = "street")
    private String street;

    @NotNull
    @Column(name = "house")
    private int house;

    @NotNull
    @Column(name = "flat")
    private int flat;

    @OneToMany(mappedBy = "address")
    private List<Order> orders;
}
