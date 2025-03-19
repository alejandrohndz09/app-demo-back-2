package com.example.dto.mapper;

import com.example.domain.Departamento;
import com.example.domain.Municipio;
import com.example.dto.*;
import jakarta.inject.Named;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi", uses = {MunicipioMapper.class})
public interface DepartamentoMapper {
    //DepartamentoMapper INSTANCE = Mappers.getMapper(DepartamentoMapper.class);

    Departamento toEntity(DepartamentoDtoRequest dto);

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
