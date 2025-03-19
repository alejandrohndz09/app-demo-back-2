package com.example.dto.mapper;

import com.example.domain.Municipio;
import com.example.dto.*;
import jakarta.inject.Named;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi" )
public interface MunicipioMapper {
    //Instancia del mapper para su uso fuera de él
   // MunicipioMapper INSTANCE = Mappers.getMapper(MunicipioMapper.class);
    Municipio toEntity(MunicipioDtoRequest dto);

    MunicipioDto toDto(Municipio entity);

     List<MunicipioDto> toSimpleMunicipios(List<Municipio> municipios) ;

}
