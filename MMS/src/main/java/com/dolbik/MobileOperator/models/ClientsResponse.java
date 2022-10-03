package com.dolbik.MobileOperator.models;

import com.dolbik.MobileOperator.dto.ClientDTO;

import java.util.List;

public class ClientsResponse {
    private List<ClientDTO> clients;

    public ClientsResponse(List<ClientDTO> clients) {
        this.clients = clients;
    }

    public List<ClientDTO> getClients() {
        return clients;
    }

    public void setClients(List<ClientDTO> clients) {
        this.clients = clients;
    }
}
