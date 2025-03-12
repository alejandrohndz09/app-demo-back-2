package com.example.dto.mapper;

import com.example.domain.Departamento;
import com.example.domain.Departamento;
import com.example.dto.DepartamentoDto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class DepartamentoMapper implements MapperService<Departamento, DepartamentoDto> {
    @Override
    public void toEntity(Departamento d, DepartamentoDto dto) {
        d.setNombre(dto.nombre());
        d.setCodigo(dto.codigo());
    }

    @Override
    public DepartamentoDto toDTO(Departamento entity) {
        return new DepartamentoDto(entity.getId(),entity.getCodigo(),
                entity.getNombre());
    }
}
