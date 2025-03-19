package com.example.dto.mapper;

import com.example.domain.Distrito;
import com.example.domain.Distrito;
import com.example.domain.Municipio;
import com.example.dto.*;
import jakarta.enterprise.context.RequestScoped;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi", uses = {MunicipioMapper.class})
public interface DistritoMapper {
   // DistritoMapper INSTANCE = Mappers.getMapper(DistritoMapper.class);

    Distrito toEntity(DistritoDtoRequest dto);

    DistritoDto toDto(Distrito entity);

    /* MAPEO AUTOMATICO
    * MapStruct detecta que municipio es un objeto y busca un método en MunicipioMapper que lo convierta en MunicipioDto.
    * Como en MunicipioMapper ya tenemos MunicipioDto toDto(Municipio entity), MapStruct automáticamente usa ese método
    * para convertir el objeto.
    * No es necesario mapear manualmente la lista en DepartamentoMapper. MapStruct lo hace
    * */
    @Mapping(target = "municipio", source = "municipio"/*, qualifiedByName = "toMunicipioDto"*/)
    DistritoDtoDetail toDtoDetail(Distrito entity);

  /*  @Named("toMunicipioDto")
    default MunicipioDto toMunicipioDto(Municipio municipio){
        return MunicipioMapper.INSTANCE.toDto(municipio);
    }*/

}
