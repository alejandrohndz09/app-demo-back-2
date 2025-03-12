package com.example.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.example.domain.Departamento}
 */
@RegisterForReflection
public record DepartamentoDto(long id, @NotBlank @Size(min = 2, max = 2)  String codigo,
                              @NotBlank @Size(min = 5, max = 80, message = "Mínimo 5 caracteres, máximo 80")  String nombre)
        implements Serializable {
}