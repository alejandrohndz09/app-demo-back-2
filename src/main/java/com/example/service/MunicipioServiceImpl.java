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
import net.sf.jasperreports.engine.JRException;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@ApplicationScoped
public class MunicipioServiceImpl implements MunicipioService {
    @Inject
    private MunicipioRepository municipioRepository;
    @Inject
    private ReportesService reportesService;
    @Inject
    private MunicipioMapper mapper;

    @Override
    public List<MunicipioDto> getMunicipios() {
        return municipioRepository.findAll()//.project(MunicipioDto.class).list();
                .stream().map(mapper::toDtoDetail).toList();
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
                .map(mapper::toDtoDetail)
                .orElseThrow(() -> new NoSuchElementException("No se encontró el registro"));
    }

    @Override
    public Response insert(MunicipioDto dto) {
        var entity = new Municipio();
        mapper.toEntity(dto, entity); //Conversion de DTO -> Entity
        municipioRepository.persist(entity); //Insercion
        municipioRepository.getEntityManager().refresh(entity);
        //Al terminar el proceso se espera que devuelva el DTO del registro insertado
        return Response.created(URI.create("/municipios/" + entity.getId()))
                .entity(mapper.toDtoDetail(entity)) //Conversión del registro insertado a DTO
                .build();
    }

    @Override
    public Response update(long id, MunicipioDto dto) {
        var original = municipioRepository.findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró registro"));

        mapper.toEntity(dto, original);
        municipioRepository.persist(original);
        // Forzar sincronización con la BD
        municipioRepository.getEntityManager().flush();
        municipioRepository.getEntityManager().refresh(original);

        return Response.ok().entity(mapper.toDtoDetail(original)).build();
    }

    @Override
    public Response delete(long id) {
       /* if (municipioRepository.deleteById(id)) {
            return Response.ok()*//*.entity("Operación exitosa.")*//*.build();
        }
        return Response.status(400)*//*.entity("No se encontró registro")*//*.build();
*/

        Optional<Municipio> municipioOpt = Optional.ofNullable(municipioRepository.findById(id));

        if (municipioOpt.isEmpty()) {
            return Response.status(400)
                    .entity(Map.of("message", "No se encontró registro."))
                    .build();
        }

        Municipio municipio = municipioOpt.get();

        if (municipio.getDistritos() != null && !municipio.getDistritos().isEmpty()) {
            return Response.status(400)
                    .entity(Map.of("message", "Este municipio no puede ser eliminado porque tiene distritos asociados."))
                    .build();
        }

        if (municipioRepository.deleteById(id)) {
            return Response.ok(Map.of("message", "Operación exitosa: municipio eliminado.")).build();
        } else {
            return Response.status(400).entity(Map.of("message", "Error desconocido.")).build();

        }
    }

    @Override
    public Response generarReporte(String format) throws JRException, SQLException {
        return reportesService.generarReporte(format, null, "municipios");
    }
}
