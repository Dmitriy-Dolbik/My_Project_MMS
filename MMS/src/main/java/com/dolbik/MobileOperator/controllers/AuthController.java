package com.dolbik.MobileOperator.controllers;

import com.dolbik.MobileOperator.models.Client;
import com.dolbik.MobileOperator.services.RegistrationService;
import com.dolbik.MobileOperator.util.ClientValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final ClientValidator clientValidator;
    private final RegistrationService registrationService;
    private final ModelMapper modelMapper;
    @Autowired
    public AuthController(ClientValidator clientValidator, RegistrationService registrationService, ModelMapper modelMapper) {
        this.clientValidator = clientValidator;
        this.registrationService = registrationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("client") Client client){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("client") @Valid Client client,
                                                          BindingResult bindingResult){
        clientValidator.validate(client, bindingResult);
        if (bindingResult.hasErrors()){
            return "auth/registration";
        }
        registrationService.register(client);
        return "redirect:/auth/login";
    }
    /*public Client convertToClient(ClientDTO clientDTO){
        return modelMapper.map(clientDTO, Client.class);
    }
    @ExceptionHandler
    private ResponseEntity<AuthErrorResponse> handleException(AuthException auth){
        AuthErrorResponse response = new AuthErrorResponse(
                auth.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }*/

}
