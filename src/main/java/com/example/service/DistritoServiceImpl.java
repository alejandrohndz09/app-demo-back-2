package com.example.service;

import com.example.domain.Distrito;
import com.example.dto.DistritoDto;
import com.example.dto.mapper.DistritoMapper;
import com.example.repository.DistritoRepository;
import com.example.service.interfaces.DistritoService;
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
public class DistritoServiceImpl implements DistritoService {
    @Inject
    private DistritoRepository distritoRepository;
    @Inject
    private DistritoMapper mapper;

    @Override
    public List<DistritoDto> getDistritos() {
        return distritoRepository.findAll().project(DistritoDto.class).list();
    }

    @Override
    public PaginatedResponse<DistritoDto> getDistritosP(int page, String q) {
        var query = distritoRepository.findAll();
        //Aplicacion de filtro
        if (q != null) {
            query.filter("buscar", Parameters.with("distrito", "%" + q + "%"));
        }
        //Paginar Resultados
        Page p = new Page(page - 1, 5);
        query.page(p);
        //Conversion de los registros a DTO
        var queryConverted = query.project(DistritoDto.class);
        //Encapsular Respuesta
        var pr = new PaginatedResponse<>(queryConverted);
        if (pr.data() != null && !pr.data().isEmpty()) {
            return pr;
        }
        throw new NoSuchElementException("No se encontraron registros");
    }

    @Override
    public DistritoDto getDistrito(long id) {
        return distritoRepository.findByIdOptional(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el registro"));
    }

    @Override
    public Response insert(DistritoDto dto) {
        var entity = new Distrito();
        mapper.toEntity(entity, dto); //Conversion de DTO -> Entity
        distritoRepository.persist(entity); //Insercion
        //Al terminar el proceso se espera que devuelva el DTO del registro insertado
        return Response.created(
                        URI.create("/distritos/" + entity.getId()))
                .entity(mapper.toDTO(entity)) //Conversión del registro insertado a DTO
                .build();
    }

    @Override
    public Response update(long id, DistritoDto dto) {
        var original = distritoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró registro"));

        mapper.toEntity(original,dto);
        distritoRepository.persist(original);
        return Response.ok().entity(mapper.toDTO(original)).build();
    }

    @Override
    public Response delete(long id) {
        if(distritoRepository.deleteById(id)){
            return Response.ok()/*.entity("Operación exitosa.")*/.build();
        }
        return Response.status(400)/*.entity("No se encontró registro")*/.build();
    }
}
