package com.example.service;

import com.example.domain.Municipio;
import com.example.dto.MunicipioDtoDetail;
import com.example.dto.MunicipioDtoRequest;
import com.example.dto.mapper.MunicipioMapper;
import com.example.repository.MunicipioRepository;
import com.example.service.interfaces.MunicipioService;
import com.example.tools.PaginatedResponse;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import net.sf.jasperreports.engine.JRException;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@ApplicationScoped
public class MunicipioServiceImpl implements MunicipioService {
    @Inject
    private MunicipioRepository municipioRepository;
    @Inject
    private ReportesService reportesService;
    @Inject
    private MunicipioMapper mapper;

    @Override
    public List<MunicipioDtoDetail> getMunicipios() {
        return municipioRepository.findAll()//.project(MunicipioDtoRequest.class).list();
                .stream().map(mapper::toDtoDetail).toList();
    }

    @Override
    public PaginatedResponse<MunicipioDtoDetail> getMunicipiosP(int page, String q) {
        var query = municipioRepository.findAll();
        //Aplicacion de filtro
        if (q != null) {
            query.filter("buscar", Parameters.with("municipio", "%" + q + "%"));
        }
        //Paginar Resultados
        Page p = new Page(page - 1, 5);
        query.page(p);
        //Conversion de los registros a DTO
        var queryConverted = query.project(MunicipioDtoDetail.class);
        //Encapsular Respuesta
        var pr = new PaginatedResponse<>(queryConverted);
        if (pr.data() != null && !pr.data().isEmpty()) {
            return pr;
        }
        throw new NoSuchElementException("No se encontraron registros");
    }

    @Override
    public MunicipioDtoDetail getMunicipio(long id) {
        return municipioRepository.findByIdOptional(id)
                .map(mapper::toDtoDetail)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el registro"));
    }

    @Override
    public Response insert(MunicipioDtoRequest dto) {
        var entity = new Municipio();
        mapper.toEntity(dto,entity); //Conversion de DTO -> Entity
        municipioRepository.persist(entity); //Insercion
        municipioRepository.getEntityManager().refresh(entity);
        //Al terminar el proceso se espera que devuelva el DTO del registro insertado
        return Response.created(URI.create("/municipios/" + entity.getId()))
                .entity(mapper.toDtoDetail(entity)) //Conversión del registro insertado a DTO
                .build();
    }

    @Override
    public Response update(long id, MunicipioDtoRequest dto) {
        var original = municipioRepository.findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró registro"));

        mapper.toEntity(dto,original);
        municipioRepository.persist(original);
        // Forzar sincronización con la BD
        municipioRepository.getEntityManager().flush();
        municipioRepository.getEntityManager().refresh(original);

        return Response.ok().entity(mapper.toDtoDetail(original)).build();
    }

    @Override
    public Response delete(long id) {
        if (municipioRepository.deleteById(id)) {
            return Response.ok()/*.entity("Operación exitosa.")*/.build();
        }
        return Response.status(400)/*.entity("No se encontró registro")*/.build();
    }
    @Override
    public Response generarReporte(String format) throws JRException, SQLException {
        return reportesService.generarReporte(format, null, "municipios");
    }
}
