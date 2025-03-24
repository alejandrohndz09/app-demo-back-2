package com.example.dto;

import com.example.dto.MunicipioDto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.domain.Distrito}
 */
public record DistritoDtoDetail(long id, long idMunicipio, String codigo, String nombre,
                                MunicipioDto municipio) implements Serializable {
}