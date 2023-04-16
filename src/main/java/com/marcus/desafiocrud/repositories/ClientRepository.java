package com.marcus.desafiocrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcus.desafiocrud.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}