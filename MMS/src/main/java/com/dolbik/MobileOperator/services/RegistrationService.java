package com.dolbik.MobileOperator.services;

import com.dolbik.MobileOperator.models.Client;
import com.dolbik.MobileOperator.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegistrationService {
    private final ClientsRepository clientsRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public RegistrationService(ClientsRepository clientsRepository, PasswordEncoder passwordEncoder) {
        this.clientsRepository = clientsRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public void register(Client client){
        String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        clientsRepository.save(client);
    }
}
