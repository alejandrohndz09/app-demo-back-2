package com.example.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.example.domain.Distrito}
 */
public record DistritoDto(long id, long idMunicipio, String codigo, String nombre) implements Serializable {
}