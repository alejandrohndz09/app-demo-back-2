package com.example.dto.mapper;

import com.example.domain.Municipio;
import com.example.dto.*;
import jakarta.inject.Named;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "jakarta-cdi", injectionStrategy = InjectionStrategy.FIELD, uses = DistritoMapper.class)
public interface MunicipioMapper {
    //Instancia del mapper para su uso fuera de él
    MunicipioMapper INSTANCE = Mappers.getMapper(MunicipioMapper.class);

    @Mapping(target = "distritos", ignore = true)
    @Mapping(target = "departamento", ignore = true)
    void toEntity(MunicipioDtoRequest dto, @MappingTarget Municipio entity);

    MunicipioDto toDto(Municipio entity);
    @Mapping(target = "departamento", source = "departamento")
    @Mapping(target = "distritos", source = "distritos")
    MunicipioDtoDetail toDtoDetail(Municipio entity);

}
