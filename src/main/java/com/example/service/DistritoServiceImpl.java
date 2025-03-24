package com.example.service;

import com.example.domain.Distrito;
import com.example.dto.DistritoDtoDetail;
import com.example.dto.DistritoDtoRequest;
import com.example.dto.mapper.DistritoMapper;
import com.example.repository.DistritoRepository;
import com.example.service.interfaces.DistritoService;
import com.example.tools.PaginatedResponse;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import net.sf.jasperreports.engine.JRException;
import org.mapstruct.factory.Mappers;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@ApplicationScoped
public class DistritoServiceImpl implements DistritoService {
    @Inject
    private DistritoRepository distritoRepository;
    @Inject
    private ReportesService reportesService;
    @Inject
    private DistritoMapper mapper;

    @Override
    public List<DistritoDtoRequest> getDistritos() {

        return distritoRepository.findAll()//.project(DistritoDtoRequest.class).list();
                .stream().map(mapper::toDtoDetail
                ).toList();
    }

    @Override
    public PaginatedResponse<DistritoDtoRequest> getDistritosP(int page, String q) {
        var query = distritoRepository.findAll();
        //Aplicacion de filtro
        if (q != null) {
            query.filter("buscar", Parameters.with("distrito", "%" + q + "%"));
        }
        //Paginar Resultados
        Page p = new Page(page - 1, 5);
        query.page(p);
        //Conversion de los registros a DTO
        var queryConverted = query.project(DistritoDtoRequest.class);
        //Encapsular Respuesta
        var pr = new PaginatedResponse<>(queryConverted);
        if (pr.data() != null && !pr.data().isEmpty()) {
            return pr;
        }
        throw new NoSuchElementException("No se encontraron registros");
    }

    @Override
    public DistritoDtoRequest getDistrito(long id) {
        return distritoRepository.findByIdOptional(id)
                .map(mapper::toDtoDetail)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el registro"));
    }

    @Override
    public Response insert(DistritoDtoRequest dto) {
        var entity = new Distrito();
        mapper.toEntity(dto,entity); //Conversion de DTO -> Entity
        distritoRepository.persist(entity); //Insercion
        distritoRepository.getEntityManager().refresh(entity);
        //Al terminar el proceso se espera que devuelva el DTO del registro insertado
        return Response.created(
                        URI.create("/distritos/" + entity.getId()))
                .entity(mapper.toDtoDetail(entity)) //Conversión del registro insertado a DTO
                .build();
    }

    @Override
    public Response update(long id, DistritoDtoRequest dto) {
        var original = distritoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró registro"));

        mapper.toEntity(dto,original);
        distritoRepository.persist(original);
        // Forzar sincronización con la BD
        distritoRepository.getEntityManager().flush();
        // Refrescar desde la BD
        distritoRepository.getEntityManager().refresh(original);
        return Response.ok().entity(mapper.toDtoDetail(original)).build();
    }

    @Override
    public Response delete(long id) {
        if(distritoRepository.deleteById(id)){
            return Response.ok()/*.entity("Operación exitosa.")*/.build();
        }
        return Response.status(400)/*.entity("No se encontró registro")*/.build();
    }
    @Override
    public Response generarReporte(String format) throws JRException, SQLException {
        return reportesService.generarReporte(format, null, "distritos");
    }
}
