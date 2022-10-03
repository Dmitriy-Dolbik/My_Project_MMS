package com.dolbik.MobileOperator.controllers;

import com.dolbik.MobileOperator.dto.ClientDTO;
import com.dolbik.MobileOperator.models.Client;
import com.dolbik.MobileOperator.services.RegistrationService;
import com.dolbik.MobileOperator.util.AuthErrorResponse;
import com.dolbik.MobileOperator.util.AuthException;
import com.dolbik.MobileOperator.util.ClientValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.dolbik.MobileOperator.util.ErrorUtil.returnErrorsToClient;

@RestController
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

    /*@GetMapping()
    public String loginPage(){
        return "auth/login";
    }
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("Client") Client client){
        return "auth/registration";
    }*/

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> performRegistration(@RequestBody @Valid ClientDTO clientDTO,
                                                          BindingResult bindingResult){
        Client client = convertToClient(clientDTO);
        clientValidator.validate(client, bindingResult);
        if (bindingResult.hasErrors()){
            returnErrorsToClient(bindingResult);
        }
        registrationService.register(client);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    public Client convertToClient(ClientDTO clientDTO){
        return modelMapper.map(clientDTO, Client.class);
    }
    @ExceptionHandler
    private ResponseEntity<AuthErrorResponse> handleException(AuthException auth){
        AuthErrorResponse response = new AuthErrorResponse(
                auth.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
