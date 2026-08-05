package com.ap.clients.services;

import com.ap.clients.dto.ClientRequestDTO;
import com.ap.clients.dto.ClientResponseDTO;
import com.ap.clients.entities.Client;
import com.ap.clients.services.exceptions.DatabaseException;
import com.ap.clients.services.exceptions.ResourceNotFoundException;
import com.ap.clients.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
