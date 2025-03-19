package com.example.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.example.domain.Departamento}
 */
public record DepartamentoDto(long id, String codigo, String nombre) implements Serializable {
}