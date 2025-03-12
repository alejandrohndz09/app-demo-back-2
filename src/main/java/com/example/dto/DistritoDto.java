package com.example.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.example.domain.Distrito}
 */
@RegisterForReflection
public record DistritoDto(long id, @Positive(message = "Debe seleccionar un municipio") long idMunicipio,
                          @NotBlank @Size(min = 3, max = 3)  String codigo,
                          @NotBlank @Size(message = "Mínimo 5 caracteres, máximo 80", min = 5, max = 80)  String nombre)
        implements Serializable {
}