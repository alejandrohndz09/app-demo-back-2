package com.example.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.example.domain.Municipio}
 */
@RegisterForReflection
public record MunicipioDto(long id, @Positive(message = "Debe seleccionar un departamento")long idDepartamento,
                           @Size(min = 3, max = 3) @NotBlank String codigo,
                           @Size(min = 5, max = 80, message = "Mínimo 5 caracteres, máximo 80") @NotBlank String nombre)
        implements Serializable {
}