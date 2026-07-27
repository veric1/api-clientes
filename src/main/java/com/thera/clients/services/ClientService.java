package com.thera.clients.services;

import com.thera.clients.dto.ClientRequestDTO;
import com.thera.clients.dto.ClientResponseDTO;
import com.thera.clients.entities.Client;
import com.thera.clients.services.exceptions.DatabaseException;
import com.thera.clients.services.exceptions.ResourceNotFoundException;
import com.thera.clients.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    private void copyDtoToClient(ClientRequestDTO dto, Client entity){
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        entity.setCpf(dto.getCpf());
        entity.setName(dto.getName());
        entity.setIncome(dto.getIncome());
    }

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientResponseDTO findById(Long id) {
        Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ClientResponseDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientResponseDTO> findAll(Pageable pageable){
        Page<Client> clients = repository.findAll(pageable);
        return clients.map(ClientResponseDTO::new);
    }

    @Transactional
    public ClientResponseDTO insert(ClientRequestDTO clientRequestDTO) {
        if (repository.existsByCpf(clientRequestDTO.getCpf())) {
            throw new DatabaseException("O CPF já está no banco");
        }
        Client client = new Client();
        copyDtoToClient(clientRequestDTO, client);
        client = repository.save(client);
        return new ClientResponseDTO(client);
    }

    @Transactional
    public ClientResponseDTO update(Long id, ClientRequestDTO clientRequestDTO){
        if (repository.existsByCpf(clientRequestDTO.getCpf())) {
            throw new DatabaseException("O CPF já está no banco");
        }
        Client client = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado"));
        copyDtoToClient(clientRequestDTO, client);
        client = repository.save(client);
        return new ClientResponseDTO(client);
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        repository.deleteById(id);
    }
}
