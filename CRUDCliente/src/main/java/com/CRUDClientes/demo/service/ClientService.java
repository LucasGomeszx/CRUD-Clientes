package com.CRUDClientes.demo.service;

import com.CRUDClientes.demo.dto.ClientDTO;
import com.CRUDClientes.demo.entities.Client;
import com.CRUDClientes.demo.repositories.ClientRepository;
import com.CRUDClientes.demo.service.exeptions.ResorceNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> getAllClients(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    @Transactional(readOnly = true)
    public ClientDTO getClientById(Long id) {
        Client client = repository.findById(id).orElseThrow(
                () -> new ResorceNotFoundExeption("Recurso nao encontrado"));
        ClientDTO dto = new ClientDTO(client);
        return dto;
    }

}
