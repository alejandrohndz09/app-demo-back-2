package com.example.dto.mapper;

import com.example.domain.Municipio;
import com.example.dto.*;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "jakarta-cdi", injectionStrategy = InjectionStrategy.FIELD, uses = {DistritoMapper.class, DepartamentoMapper.class})
public interface MunicipioMapper {
    //Instancia del mapper para su uso fuera de él
    MunicipioMapper INSTANCE = Mappers.getMapper(MunicipioMapper.class);

    @Mapping(target = "departamento", ignore = true)
    @Mapping(target = "distritos", ignore = true)
    public MunicipioDto toDto(Municipio entity);
    @Mapping(target = "departamento", expression = "java(DepartamentoMapper.INSTANCE.toDto(entity.getDepartamento()))")
    @Mapping(target = "distritos",  expression = "java(entity.getDistritos().stream().map(DistritoMapper.INSTANCE::toDto).toList())")
    MunicipioDto toDtoDetail(Municipio entity);
    @Mapping(target = "distritos", ignore = true)
    @Mapping(target = "departamento", ignore = true)
    void toEntity(MunicipioDto dto, @MappingTarget Municipio entity);

}
