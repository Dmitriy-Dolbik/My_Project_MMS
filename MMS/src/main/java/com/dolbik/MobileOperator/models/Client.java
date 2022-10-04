package com.dolbik.MobileOperator.models;

import com.dolbik.MobileOperator.util.DateDeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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
    @NotNull(message = "Name must be not empty")
    @Size(max=100, message="Name must be shorter than 100 characters")
    private String name;

    @Column(name="surname")
    @NotNull(message = "Surname should be not empty")
    @Size(message = "Surname should be shorter than 100 characters")
    private String surname;

    @Column(name="date_of_birth")
    @NotNull(message = "Date of birth must be not empty")
    @Temporal(TemporalType.DATE)
    /*@DateTimeFormat(pattern = "yyyy-MM-dd")*/
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Past
    private Date dateOfBirth;

    @Column(name="email")
    @Email(message = "Email must be valid")
    @NotNull(message = "Email must be not empty")
    private String email;

    @Column(name="password")
    private String password;

    @OneToMany(mappedBy="client")
    private List<Order> orders;

}
