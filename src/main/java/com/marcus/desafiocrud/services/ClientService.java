package com.marcus.desafiocrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marcus.desafiocrud.dto.ClientDTO;
import com.marcus.desafiocrud.entities.Client;
import com.marcus.desafiocrud.repositories.ClientRepository;
import com.marcus.desafiocrud.services.excepitions.DatabaseException;
import com.marcus.desafiocrud.services.excepitions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    

//FIND BY ID
    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Recurso não encontrado"));
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

        try{
            Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);    
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        } 

    }
//UPDATE
    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){

        try{
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }

       

    }
//DELETE
    @Transactional
    public void delete(Long id){
        
        try{
            if(!repository.existsById(id)){
                throw new ResourceNotFoundException("Recurso não encontrado");
            }
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
                throw new DatabaseException("Falha de integridade referencial");
        }             

    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {

        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        entity.setIncome(dto.getIncome());
    }


    
}
