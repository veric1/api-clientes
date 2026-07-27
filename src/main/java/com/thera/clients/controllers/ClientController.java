package com.thera.clients.controllers;

import com.thera.clients.dto.ClientRequestDTO;
import com.thera.clients.dto.ClientResponseDTO;
import com.thera.clients.services.ClientService;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id) {
        ClientResponseDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ClientResponseDTO>> findAll(Pageable pageable){
        Page<ClientResponseDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> insert(@Valid @RequestBody ClientRequestDTO clientRequestDTO){
        ClientResponseDTO dto = service.insert(clientRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ClientRequestDTO clientRequestDTO){
        ClientResponseDTO dto = service.update(id, clientRequestDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
