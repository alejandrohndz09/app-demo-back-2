package com.example.dto.mapper;

import com.example.domain.Departamento;
import com.example.dto.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "jakarta-cdi", injectionStrategy = InjectionStrategy.FIELD, uses = {MunicipioMapper.class})
public interface DepartamentoMapper {
    DepartamentoMapper INSTANCE = Mappers.getMapper(DepartamentoMapper.class);

    @Mapping(target = "municipios", ignore = true)
    DepartamentoDto toDto(Departamento entity);
    @Mapping(target = "municipios",expression = "java(entity.getMunicipios().stream().map(MunicipioMapper.INSTANCE::toDto).toList())")
    DepartamentoDto toDtoDetail(Departamento entity);
    @Mapping(target = "municipios", ignore = true)
    void toEntity(DepartamentoDto dto, @MappingTarget Departamento entity);
}
