package com.dolbik.MobileOperator.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Client")
@Data
@NoArgsConstructor
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name must be not empty")
    @Size(max=100, message="Name must be shorter than 100 characters")
    private String name;

    @Column(name="surname")
    @NotEmpty(message = "Surname should be not empty")
    @Size(message = "Surname should be shorter than 100 characters")
    private String surname;

    @Column(name="date_of_birth")
    @NotEmpty(message = "Date of birth must be not empty")
    private LocalDate dateOfBirth;

    @Column(name="email")
    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email must be not empty")
    private String email;

    @Column(name="password")
    private String password;

    @OneToMany(mappedBy="client")
    private List<Order> orders;

}
