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

    DepartamentoDto toDto(Departamento entity);

    @Mapping(target = "municipios", source = "municipios"/*, qualifiedByName = "toSimpleMunicipios"*/)
    DepartamentoDtoDetail toDtoDetail(Departamento entity);

  /*  *//* En este caso se mappeo manualmente el listado de <municipioDto>, pero se puede hacer automáticamente
    véase el ejemplo en DistritoMapper*//*
    @Named("toSimpleMunicipios")
    default List<MunicipioDto> toSimpleMunicipios(List<Municipio> municipios) {
        return municipios == null ? List.of() :
                municipios.stream()
                        .map(MunicipioMapper.INSTANCE::toDto)
                        .toList();
    }*/
}
