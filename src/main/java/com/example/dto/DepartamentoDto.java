package com.example.dto;

import com.example.domain.Departamento;
import com.example.validation.UniqueValue;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.PostPersist;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.example.domain.Departamento}
 */
@RegisterForReflection
public record DepartamentoDto(long id,
                              @NotBlank(message = "Campo requerido.")
                              @Size(min = 2, max = 2, message = "El código debe tener 2 carateres.")
                              @UniqueValue(entity = Departamento.class, field = "codigo", /*idField = "id",*/ message = "El código ya está registrado")
                              String codigo,
                              @NotBlank(message = "Campo requerido.")
                              @Size(min = 5, max = 80, message = "Mínimo 5 caracteres, máximo 80.")
                              String nombre)
        implements Serializable {
}