package com.marcus.desafiocrud.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcus.desafiocrud.dto.ClientDTO;
import com.marcus.desafiocrud.entities.Client;
import com.marcus.desafiocrud.repositories.ClientRepository;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Optional<Client> result = repository.findById(id);
        Client client = result.get();
        return new ClientDTO(client);
        
     }
     @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){

        Page<Client> resul =repository.findAll(pageable);
        return resul.map(x -> new ClientDTO(x));
    }

}
