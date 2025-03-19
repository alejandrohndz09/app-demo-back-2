package com.example.dto.mapper;

import com.example.domain.Distrito;
import com.example.domain.Municipio;
import com.example.dto.DistritoDto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class DistritoMapper implements MapperService<Distrito, DistritoDto> {
     @Override
    public void toEntity(Distrito d, DistritoDto dto) {
        d.setNombre(dto.nombre());
        d.setCodigo(dto.codigo());
        d.setIdMunicipio(dto.idMunicipio());

    }

    @Override
    public DistritoDto toDTO(Distrito entity) {
        return new DistritoDto(entity.getId(), entity.getIdMunicipio(), entity.getCodigo(),
                entity.getNombre(), entity.getMunicipio()==null?null:entity.getMunicipio());
    }
}
