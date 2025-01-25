package com.CRUDClientes.demo.controllers;

import com.CRUDClientes.demo.dto.ClientDTO;
import com.CRUDClientes.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAllClient(Pageable pageable) {
        Page<ClientDTO> result = service.getAllClients(pageable);
        return ResponseEntity.ok(result);
    }

}
