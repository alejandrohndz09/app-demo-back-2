package com.example.dto;

import com.example.domain.Departamento;
import com.example.domain.Distrito;
import com.example.domain.Municipio;
import com.example.dto.mapper.MunicipioMapper;
import com.example.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;

import java.io.Serializable;

/**
 * DTO for {@link com.example.domain.Distrito}
 */
@RegisterForReflection
public record DistritoDto(long id,
                          @Positive(message = "Debe seleccionar un municipio.")
                          long idMunicipio,
                          @NotBlank(message = "Campo requerido.")
                          @Size(min = 3, max = 3, message = "El código debe tener 3 carateres.")
                          @UniqueValue(entity = Distrito.class, field = "codigo", message = "El código ya está registrado.", groups = DistritoDto.OnCreate.class)
                          String codigo,
                          @NotBlank(message = "Campo requerido.")
                          @Size(message = "Mínimo 5 caracteres, máximo 80.", min = 5, max = 80)
                          String nombre,
                          @JsonBackReference
                          MunicipioDto municipio)
        implements Serializable {

    static MunicipioMapper municipioMapper = new MunicipioMapper();

    public DistritoDto(long id, long idMunicipio, String codigo, String nombre, Municipio municipio) {
        this(id, idMunicipio, codigo, nombre, municipioMapper.toDTO(municipio));
    }

    public interface OnCreate extends Default {} // Validaciones al crear
    public interface OnUpdate {} // Validaciones al actualizar
}