package com.example.dto;

import com.example.domain.Municipio;
import com.example.dto.mapper.MunicipioMapper;
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
                          @NotBlank @Size(min = 3, max = 3) String codigo,
                          @NotBlank @Size(message = "Mínimo 5 caracteres, máximo 80", min = 5, max = 80) String nombre,
                          MunicipioDto municipio)
        implements Serializable {

    static MunicipioMapper municipioMapper = new MunicipioMapper();

    public DistritoDto(long id, long idMunicipio, String codigo, String nombre, Municipio municipio) {
        this(id, idMunicipio, codigo, nombre, municipioMapper.toDTO(municipio));
    }
}