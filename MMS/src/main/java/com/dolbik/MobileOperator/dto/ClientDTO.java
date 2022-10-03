package com.dolbik.MobileOperator.dto;

import com.dolbik.MobileOperator.models.Order;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class ClientDTO {

    @NotEmpty(message = "Name must be not empty")
    @Size(max=100, message="Name must be shorter than 100 characters")
    private String name;

    @NotEmpty(message = "Surname should be not empty")
    @Size(message = "Surname should be shorter than 100 characters")
    private String surname;

    @NotEmpty(message = "Date of birth must be not empty")
    private LocalDate dateOfBirth;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email must be not empty")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
