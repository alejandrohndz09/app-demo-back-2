package com.example.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.domain.Departamento}
 */
public record DepartamentoDtoDetail(long id, String codigo, String nombre,
                                    List<MunicipioDto> municipios) implements Serializable {
}