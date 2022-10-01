package com.dolbik.MobileOperator.models;

import com.dolbik.MobileOperator.models.enums.DeliveryWay;
import com.dolbik.MobileOperator.models.enums.OrderStatus;
import com.dolbik.MobileOperator.models.enums.PaymentMethod;
import com.dolbik.MobileOperator.models.enums.PaymentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Order")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name="delivery_way")
    @Enumerated(EnumType.STRING)
    private DeliveryWay deliveryWay;

    @Column(name="payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name="order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToMany
    @JoinTable(
            name="Order_Item",
            joinColumns = @JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name="item_id"))
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name="address_id", referencedColumnName="id")
    private Address address;

    @ManyToOne
    @JoinColumn(name="client_id", referencedColumnName="id")
    private Client client;


}
