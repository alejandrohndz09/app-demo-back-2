package com.example.dto;

import com.example.domain.Departamento;
import com.example.domain.Municipio;
import com.example.dto.mapper.MunicipioMapper;
import com.example.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.common.ProjectedFieldName;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.domain.Departamento}
 */
@RegisterForReflection
public record DepartamentoDto(long id,
                              @NotBlank(message = "Campo requerido.")
                              @Size(min = 2, max = 2, message = "El código debe tener 2 carateres.")
                              @UniqueValue(entity = Departamento.class, field = "codigo", message = "El código ya está registrado.", groups = DepartamentoDto.OnCreate.class)
                              String codigo,
                              @NotBlank(message = "Campo requerido.")
                              @Size(min = 5, max = 80, message = "Mínimo 5 caracteres, máximo 80.")
                              String nombre,
                              @JsonManagedReference()
                              List<MunicipioDto> municipios)
        implements Serializable {
    static MunicipioMapper municipioMapper = new MunicipioMapper();


/*
    public DepartamentoDto(long id, String codigo, String nombre,  @ProjectedFieldName("municipios")  List<Municipio> list) {
        List<MunicipioDto> municipios=list==null?List.of(): list.stream().map(municipioMapper::toDTO).toList();
        this(id, codigo, nombre, municipios);
    }
*/

    public interface OnCreate extends Default {
    } // Validaciones al crear

    public interface OnUpdate {
    } // Validaciones al actualizar

}