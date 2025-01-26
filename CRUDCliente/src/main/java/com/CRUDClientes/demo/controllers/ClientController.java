package com.CRUDClientes.demo.controllers;

import com.CRUDClientes.demo.dto.ClientDTO;
import com.CRUDClientes.demo.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAllClient(Pageable pageable) {
        Page<ClientDTO> result = service.getAllClients(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findClientById(@PathVariable Long id) {
        ClientDTO dto = service.getClientById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createNewClient(@Valid @RequestBody ClientDTO dto) {
        dto = service.createNewClient(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO dto) {
        dto = service.updateClient(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Long id) {
        service.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }

}
