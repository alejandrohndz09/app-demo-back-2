package com.example.dto;

import com.example.domain.Municipio;
import com.example.validation.UniqueValue;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.domain.Municipio}
 */
@RegisterForReflection
public record MunicipioDto(long id,
                           @Positive(message = "Debe seleccionar un departamento.")
                                  long idDepartamento,
                           @NotBlank(message = "Campo requerido.")
                                  @Size(min = 3, max = 3, message = "El código debe tener 3 carateres.")
                                  @UniqueValue(entity = Municipio.class, field = "codigo", message = "El código ya está registrado.", groups = MunicipioDto.OnCreate.class)
                                  String codigo,
                           @NotBlank(message = "Campo requerido.")
                                  @Size(min = 5, max = 80, message = "Mínimo 5 caracteres, máximo 80.")
                                  String nombre,
                           DepartamentoDto departamento,
                           List<DistritoDto> distritos)
        implements Serializable {
    public interface OnCreate extends Default {
    } // Validaciones al crear

//    public interface OnUpdate {
//    } // Validaciones al actualizar
}