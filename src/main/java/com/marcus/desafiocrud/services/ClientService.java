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

//FIND BY ID
    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Optional<Client> result = repository.findById(id);
        Client client = result.get();
        return new ClientDTO(client);
//FIND ALL
     }
     @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){

        Page<Client> resul =repository.findAll(pageable);
        return resul.map(x -> new ClientDTO(x));
    }
//INSERT
    @Transactional
    public ClientDTO insert(ClientDTO dto){

        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);

    }
//UPDATE
    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){

       Client entity = repository.getReferenceById(id);
       copyDtoToEntity(dto, entity);
       entity = repository.save(entity);
       return new ClientDTO(entity);

    }
//DELETE
    @Transactional
    public void delete(Long id){
        repository.deleteById(id);

    }




    private void copyDtoToEntity(ClientDTO dto, Client entity) {

        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        entity.setIncome(dto.getIncome());
    }

}
