package com.example.dto.mapper;

import com.example.domain.Departamento;
import com.example.domain.Municipio;
import com.example.dto.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "jakarta-cdi", injectionStrategy = InjectionStrategy.FIELD, uses = {MunicipioMapper.class})
public interface DepartamentoMapper {
    DepartamentoMapper INSTANCE = Mappers.getMapper(DepartamentoMapper.class);

    @Mapping(target = "municipios", ignore = true)
    void toEntity(DepartamentoDtoRequest dto,  @MappingTarget Departamento entity);
    @Named("toDtoDepartamento")
    @Mapping(target = "municipios", ignore = true)
    DepartamentoDtoRequest toDto(Departamento entity);

    @Mapping(target = "municipios", source = "municipios", qualifiedByName = "toDtoMunicipio")//deseo mapear los municipios simples O sea toDTO en el MunicipioMapper
    DepartamentoDtoRequest toDtoDetail(Departamento entity);
}
