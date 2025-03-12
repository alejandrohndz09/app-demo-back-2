package com.example.service;

import com.example.domain.Departamento;
import com.example.dto.DepartamentoDto;
import com.example.dto.mapper.DepartamentoMapper;
import com.example.repository.DepartamentoRepository;
import com.example.service.interfaces.DepartamentoService;
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
public class DepartamentoServiceImpl implements DepartamentoService {
    @Inject
    private DepartamentoRepository departamentoRepository;
    @Inject
    private DepartamentoMapper mapper;

    @Override
    public List<DepartamentoDto> getDepartamentos() {
        return departamentoRepository.findAll().project(DepartamentoDto.class).list();
    }

    @Override
    public PaginatedResponse<DepartamentoDto> getDepartamentosP(int page, String q) {
        var query = departamentoRepository.findAll();
        //Aplicacion de filtro
        if (q != null) {
            query.filter("buscar", Parameters.with("departamento", "%" + q + "%"));
        }
        //Paginar Resultados
        Page p = new Page(page - 1, 5);
        query.page(p);
        //Conversion de los registros a DTO
        var queryConverted = query.project(DepartamentoDto.class);
        //Encapsular Respuesta
        var pr = new PaginatedResponse<>(queryConverted);
        if (pr.data() != null && !pr.data().isEmpty()) {
            return pr;
        }
        throw new NoSuchElementException("No se encontraron registros");
    }

    @Override
    public DepartamentoDto getDepartamento(long id) {
        return departamentoRepository.findByIdOptional(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el registro"));
    }

    @Override
    public Response insert(DepartamentoDto dto) {
        var entity = new Departamento();
        mapper.toEntity(entity, dto); //Conversion de DTO -> Entity
        departamentoRepository.persist(entity); //Insercion
        //Al terminar el proceso se espera que devuelva el DTO del registro insertado
        return Response.created(
                        URI.create("/departamentos/" + entity.getId()))
                .entity(mapper.toDTO(entity)) //Conversión del registro insertado a DTO
                .build();
    }

    @Override
    public Response update(long id, DepartamentoDto dto) {
        var original = departamentoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró registro"));

        mapper.toEntity(original,dto);
        departamentoRepository.persist(original);
        return Response.ok().entity(mapper.toDTO(original)).build();
    }

    @Override
    public Response delete(long id) {
        if(departamentoRepository.deleteById(id)){
            return Response.ok()/*.entity("Operación exitosa.")*/.build();
        }
        return Response.status(400)/*.entity("No se encontró registro")*/.build();
    }
}
