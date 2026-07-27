package com.ap.clients.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;

public class ClientRequestDTO {

    @NotBlank(message = "Campo requerido")
    @Size(min = 3, max = 50, message = "Tamanho entre 3 e 50 caracteres")
    private String name;
    @NotBlank(message = "Campo requerido")
    @Size(min = 11, max = 11, message = "O CPF tem tamanho fixo de 11 números")
    private String cpf;
    @NotNull(message = "Campo requerido")
    @PositiveOrZero(message = "Valor não pode ser negativo!")
    private Double income;
    @NotNull(message = "Campo requerido")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Data deve ser anterior ao dia atual!")
    private LocalDate birthDate;
    @NotNull(message = "Campo requerido")
    @PositiveOrZero(message = "Valor não pode ser negativo!")
    private Integer children;

    public ClientRequestDTO() {
    }

    public String getName() {
        return name;
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
