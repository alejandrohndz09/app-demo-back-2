package com.example.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.domain.Municipio}
 */
public record MunicipioDtoDetail(long id, long idDepartamento, String codigo, String nombre,
                                 DepartamentoDto departamento, List<DistritoDto> distritos
) implements Serializable {
}