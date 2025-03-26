package com.example.dto.mapper;

import com.example.domain.Distrito;
import com.example.dto.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "jakarta-cdi", injectionStrategy = InjectionStrategy.FIELD, uses = {MunicipioMapper.class})
public interface DistritoMapper {
    DistritoMapper INSTANCE = Mappers.getMapper(DistritoMapper.class);
    @Mapping(target = "municipio", ignore = true)
    DistritoDto toDto(Distrito entity);

    /* MAPEO AUTOMATICO
    * MapStruct detecta que municipio es un objeto y busca un método en MunicipioMapper que lo convierta en MunicipioDto.
    * Como en MunicipioMapper ya tenemos MunicipioDto toDto(Municipio entity), MapStruct automáticamente usa ese método
    * para convertir el objeto.
    * No es necesario mapear manualmente la lista en DepartamentoMapper. MapStruct lo hace
    * */
    @Mapping(target = "municipio",  expression = "java(MunicipioMapper.INSTANCE.toDto(entity.getMunicipio()))")
    DistritoDto toDtoDetail(Distrito entity);
    @Mapping(target = "municipio", ignore = true)
    void toEntity(DistritoDto dto, @MappingTarget Distrito entity);
}
