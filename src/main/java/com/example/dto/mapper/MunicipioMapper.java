package com.example.dto.mapper;

import com.example.domain.Municipio;
import com.example.dto.MunicipioDto;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class MunicipioMapper implements MapperService<Municipio, MunicipioDto> {
    @Override
    public void toEntity(Municipio d, MunicipioDto dto) {
        d.setNombre(dto.nombre());
        d.setCodigo(dto.codigo());
        d.setIdDepartamento(dto.idDepartamento());
    }

    @Override
    public MunicipioDto toDTO(Municipio entity) {
        return new MunicipioDto(entity.getId(),entity.getIdDepartamento(), entity.getCodigo(),
                entity.getNombre());
    }
}
