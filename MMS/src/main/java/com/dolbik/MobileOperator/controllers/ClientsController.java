package com.dolbik.MobileOperator.controllers;

import com.dolbik.MobileOperator.dto.ClientDTO;
import com.dolbik.MobileOperator.models.Client;
import com.dolbik.MobileOperator.models.ClientsResponse;
import com.dolbik.MobileOperator.services.ClientsService;
import com.dolbik.MobileOperator.util.ClientErrorResponse;
import com.dolbik.MobileOperator.util.ClientNotAddException;
import com.dolbik.MobileOperator.util.ClientNotFoundException;
import com.dolbik.MobileOperator.util.ClientValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static com.dolbik.MobileOperator.util.ErrorUtil.returnErrorsToClient;

@RestController
@RequestMapping("/clients")
public class ClientsController {
    private final ModelMapper modelMapper;
    private final ClientsService clientsService;
    private final ClientValidator clientValidator;
    @Autowired
    public ClientsController(ModelMapper modelMapper, ClientsService clientsService, ClientValidator clientValidator) {
        this.modelMapper = modelMapper;
        this.clientsService = clientsService;
        this.clientValidator = clientValidator;
    }
    @GetMapping()
    public ClientsResponse getClients(){
        return new ClientsResponse(clientsService.findAll()
                .stream().map(this::convertToClientDTO)
                .collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ClientDTO getClient(@PathVariable("id") int id){
        return convertToClientDTO(clientsService.findOne(id));
    }
    @PostMapping()
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid ClientDTO clientDTO,
                                          BindingResult bindingResult){
        Client client = convertToClient(clientDTO);
        clientValidator.validate(client, bindingResult);

        if (bindingResult.hasErrors()){
            returnErrorsToClient(bindingResult);
        }
        clientsService.addClient(client);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public ClientDTO convertToClientDTO(Client client){
        return modelMapper.map(client, ClientDTO.class);
    }
    public Client convertToClient(ClientDTO clientDTO){
        return modelMapper.map(clientDTO, Client.class);
    }
    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handleException(ClientNotFoundException cnfe){
        ClientErrorResponse response = new ClientErrorResponse(
                cnfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handleException(ClientNotAddException cnae){
        ClientErrorResponse response = new ClientErrorResponse(
                cnae.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }



}
