package com.example.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.example.domain.Municipio}
 */
public record MunicipioDto(long id, long idDepartamento, String codigo, String nombre) implements Serializable {
}