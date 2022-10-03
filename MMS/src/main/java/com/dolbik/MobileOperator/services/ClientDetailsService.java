package com.dolbik.MobileOperator.services;

import com.dolbik.MobileOperator.models.Client;
import com.dolbik.MobileOperator.repositories.ClientsRepository;
import com.dolbik.MobileOperator.security.ClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientDetailsService implements UserDetailsService {
    private final ClientsRepository clientsRepository;
    @Autowired
    public ClientDetailsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Client> client = clientsRepository.findByEmail(email);
        if (client.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new ClientDetails(client.get());
    }
}
