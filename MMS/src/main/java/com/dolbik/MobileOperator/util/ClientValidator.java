package com.dolbik.MobileOperator.util;

import com.dolbik.MobileOperator.models.Client;
import com.dolbik.MobileOperator.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClientValidator implements Validator {
    private final ClientsService clientsService;
    @Autowired
    public ClientValidator(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client addedClient = (Client) target;
        if (clientsService.findByEmail(addedClient.getEmail()).isPresent()){
            errors.rejectValue("email","","the Client with this email is already exists");
        }
    }
}
