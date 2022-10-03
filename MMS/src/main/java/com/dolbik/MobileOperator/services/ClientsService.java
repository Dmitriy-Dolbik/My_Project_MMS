package com.dolbik.MobileOperator.services;

import com.dolbik.MobileOperator.models.Client;
import com.dolbik.MobileOperator.repositories.ClientsRepository;
import com.dolbik.MobileOperator.util.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientsService {
    private final ClientsRepository clientsRepository;
    @Autowired
    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }
    public List<Client> findAll(){
        return clientsRepository.findAll();
    }
    public Client findOne(int id){
        Optional<Client> foundClient = clientsRepository.findById(id);
        if (foundClient.isPresent()){
            return foundClient.get();
        } else
            throw new ClientNotFoundException("The client with this Id was not found");
        //короткая запись
        // return foundClient.orElseThrow(ClientNotFoundException::new);
    }
    public Optional<Client> findByEmail(String email){
        return clientsRepository.findByEmail(email);
    }
    public void addClient(Client client){
        clientsRepository.save(client);
    }



}
