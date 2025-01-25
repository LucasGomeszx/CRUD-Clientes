package com.CRUDClientes.demo.repositories;

import com.CRUDClientes.demo.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
