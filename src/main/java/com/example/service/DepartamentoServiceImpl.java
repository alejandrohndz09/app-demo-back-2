package com.example.service;

import com.example.domain.Departamento;
import com.example.dto.DepartamentoDtoDetail;
import com.example.dto.DepartamentoDtoRequest;
import com.example.dto.mapper.DepartamentoMapper;
import com.example.repository.DepartamentoRepository;
import com.example.service.interfaces.DepartamentoService;
import com.example.tools.PaginatedResponse;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@ApplicationScoped
public class DepartamentoServiceImpl implements DepartamentoService {
    @Inject
    private DepartamentoRepository departamentoRepository;
    @Inject
    private DepartamentoMapper mapper;

    @Override
    public List<DepartamentoDtoDetail> getDepartamentos() {
        return departamentoRepository.findAll()/*.stream().map(d->mapper.toDTO(d)).toList();*/
                .stream()
                .map(mapper::toDtoDetail)
                .toList();
    }//


    @Override
    public PaginatedResponse<DepartamentoDtoDetail> getDepartamentosP(int page, String q) {
        var query = departamentoRepository.findAll();
        //Aplicacion de filtro
        if (q != null) {
            query.filter("buscar", Parameters.with("departamento", "%" + q + "%"));
        }
        //Paginar Resultados
        Page p = new Page(page - 1, 5);
        query.page(p);
        //Conversion de los registros a DTO
        var queryConverted = query.project(DepartamentoDtoDetail.class);
        //Encapsular Respuesta
        var pr = new PaginatedResponse<>(queryConverted);
        if (pr.data() != null && !pr.data().isEmpty()) {
            return pr;
        }
        throw new NoSuchElementException("No se encontraron registros");
    }

    @Override
    public DepartamentoDtoDetail getDepartamento(long id) {
        return departamentoRepository.findByIdOptional(id)
                .map(mapper::toDtoDetail)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el registro"));
    }

    @Override
    public Response insert(DepartamentoDtoRequest dto) {
        var entity = new Departamento();
        mapper.toEntity(dto,entity); //Conversion de DTO -> Entity

        departamentoRepository.persist(entity); //Insercion
        //Al terminar el proceso se espera que devuelva el DTO del registro insertado
        return Response.created(URI.create("/departamentos/" + entity.getId()))
                .entity(mapper.toDtoDetail(entity)) //Conversión del registro insertado a DTO
                .build();
    }

    @Override
    public Response update(long id, DepartamentoDtoRequest dto) {
        var original = departamentoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró registro"));
        mapper.toEntity(dto,original);
        departamentoRepository.persist(original);
        return Response.ok().entity(mapper.toDtoDetail(original)).build();
    }

    @Override
    public Response delete(long id) {
        Optional<Departamento> departamentoOpt = Optional.ofNullable(departamentoRepository.findById(id));

        if (departamentoOpt.isEmpty()) {
            return Response.status(400)
                    .entity(Map.of("message", "No se encontró registro."))
                    .build();
        }

        Departamento departamento = departamentoOpt.get();

        if (departamento.getMunicipios() != null && !departamento.getMunicipios().isEmpty()) {
            return Response.status(400)
                    .entity(Map.of("message", "Este departamento no puede ser eliminado porque tiene municipios asociados."))
                    .build();
        }

        if (departamentoRepository.deleteById(id)) {
            return Response.ok(Map.of("message", "Operación exitosa: departamento eliminado.")).build();
        } else {
            return Response.status(400).entity(Map.of("message", "Error desconocido.")).build();

        }

    }
}
