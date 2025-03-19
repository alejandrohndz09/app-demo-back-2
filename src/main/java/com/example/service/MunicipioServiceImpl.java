package com.example.service;

import com.example.domain.Municipio;
import com.example.dto.MunicipioDto;
import com.example.dto.mapper.MunicipioMapper;
import com.example.repository.MunicipioRepository;
import com.example.service.interfaces.MunicipioService;
import com.example.tools.PaginatedResponse;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@ApplicationScoped
public class MunicipioServiceImpl implements MunicipioService {
    @Inject
    private MunicipioRepository municipioRepository;
    @Inject
    private MunicipioMapper mapper;

    @Override
    public List<MunicipioDto> getMunicipios() {
        return municipioRepository.findAll()//.project(MunicipioDto.class).list();
                .stream().map(municipio -> {
                    municipio.getDepartamento().setMunicipios(null);
                    return mapper.toDTO(municipio);
                }).toList();
    }

    @Override
    public PaginatedResponse<MunicipioDto> getMunicipiosP(int page, String q) {
        var query = municipioRepository.findAll();
        //Aplicacion de filtro
        if (q != null) {
            query.filter("buscar", Parameters.with("municipio", "%" + q + "%"));
        }
        //Paginar Resultados
        Page p = new Page(page - 1, 5);
        query.page(p);
        //Conversion de los registros a DTO
        var queryConverted = query.project(MunicipioDto.class);
        //Encapsular Respuesta
        var pr = new PaginatedResponse<>(queryConverted);
        if (pr.data() != null && !pr.data().isEmpty()) {
            return pr;
        }
        throw new NoSuchElementException("No se encontraron registros");
    }

    @Override
    public MunicipioDto getMunicipio(long id) {
        return municipioRepository.findByIdOptional(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el registro"));
    }

    @Override
    public Response insert(MunicipioDto dto) {
        var entity = new Municipio();
        mapper.toEntity(entity, dto); //Conversion de DTO -> Entity
        municipioRepository.persist(entity); //Insercion
        //Al terminar el proceso se espera que devuelva el DTO del registro insertado
        return Response.created(
                        URI.create("/municipios/" + entity.getId()))
                .entity(mapper.toDTO(entity)) //Conversión del registro insertado a DTO
                .build();
    }

    @Override
    public Response update(long id, MunicipioDto dto) {
        var original = municipioRepository.findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró registro"));

        mapper.toEntity(original,dto);
        municipioRepository.persist(original);
        return Response.ok().entity(mapper.toDTO(original)).build();
    }

    @Override
    public Response delete(long id) {
        if(municipioRepository.deleteById(id)){
            return Response.ok()/*.entity("Operación exitosa.")*/.build();
        }
        return Response.status(400)/*.entity("No se encontró registro")*/.build();
    }
}
