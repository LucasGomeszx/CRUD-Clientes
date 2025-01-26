package com.CRUDClientes.demo.service;

import com.CRUDClientes.demo.dto.ClientDTO;
import com.CRUDClientes.demo.entities.Client;
import com.CRUDClientes.demo.repositories.ClientRepository;
import com.CRUDClientes.demo.service.exeptions.ResorceNotFoundExeption;
import jakarta.persistence.EntityNotFoundException;
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

    @Transactional
    public ClientDTO createNewClient(ClientDTO dto) {
        Client client = new Client();
        insertClientData(dto, client);
        client = repository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO updateClient(Long id, ClientDTO dto) {
        try {
            Client client = repository.getReferenceById(id);
            insertClientData(dto, client);
            client = repository.save(client);
            return new ClientDTO(client);
        } catch (EntityNotFoundException e) {
            throw new ResorceNotFoundExeption("Recurso nao encontrado");
        }
    }

    @Transactional
    public void deleteClientById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResorceNotFoundExeption("Recurso nao encontrado");
        } else  {
            repository.deleteById(id);
        }
    }

    private void insertClientData(ClientDTO dto, Client client) {
        client.setName(dto.getName());
        client.setChildren(dto.getChildren());
        client.setBirthDate(dto.getBirthDate());
        client.setCpf(dto.getCpf());
        client.setIncame(dto.getIncome());
    }

}
