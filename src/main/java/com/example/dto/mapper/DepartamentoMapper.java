package com.example.dto.mapper;

import com.example.domain.Departamento;
import com.example.domain.Departamento;
import com.example.dto.DepartamentoDto;
import com.example.dto.MunicipioDto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class DepartamentoMapper implements MapperService<Departamento, DepartamentoDto> {
    MunicipioMapper municipioMapper= new MunicipioMapper();
    @Override
    public void toEntity(Departamento d, DepartamentoDto dto) {
        d.setNombre(dto.nombre());
        d.setCodigo(dto.codigo());
    }

    @Override
    public DepartamentoDto toDTO(Departamento entity) {
        entity.getMunicipios();
        List<MunicipioDto> municipios=entity.getMunicipios()==null||entity.getMunicipios().isEmpty()? null:entity.getMunicipios().stream().map(municipioMapper::toDTO).toList();
        return new DepartamentoDto(entity.getId(),entity.getCodigo(),
                entity.getNombre(), municipios);
    }
}
