package com.ap.clients.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ap.clients.entities.Client;

import java.time.LocalDate;

public class ClientResponseDTO {
    private Long id;
    private String name;
    private String cpf;
    private Double income;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate birthDate;
    private Integer children;

    public ClientResponseDTO() {
    }

    public ClientResponseDTO(Client client) {
        this.id = client.getId();
        this.cpf = client.getCpf();
        this.birthDate = client.getBirthDate();
        this.name = client.getName();
        this.income = client.getIncome();
        this.children = client.getChildren();
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
