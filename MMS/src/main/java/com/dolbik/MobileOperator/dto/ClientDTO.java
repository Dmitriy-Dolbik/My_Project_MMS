package com.dolbik.MobileOperator.dto;

import com.dolbik.MobileOperator.models.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class ClientDTO {

    @NotNull(message = "Name must be not empty")
    @Size(max=100, message="Name must be shorter than 100 characters")
    private String name;

    @NotNull(message = "Surname should be not empty")
    @Size(message = "Surname should be shorter than 100 characters")
    private String surname;

    @NotNull(message = "Date of birth must be not empty")
    private LocalDate dateOfBirth;

    @Email(message = "Email must be valid")
    @NotNull(message = "Email must be not empty")
    private String email;

    private String password;
}
