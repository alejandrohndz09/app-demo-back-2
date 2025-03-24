package com.example.resource;

import com.example.dto.DepartamentoDtoRequest;
import com.example.dto.DepartamentoDtoRequest;
import com.example.service.ReportesService;
import com.example.service.interfaces.DepartamentoService;
import com.example.tools.PaginatedResponse;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.groups.ConvertGroup;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import net.sf.jasperreports.engine.JRException;
import java.sql.SQLException;
import java.util.List;

@Path("departamentos")
public class DepartamentoResource {

    @Inject
    DepartamentoService departamentoService;

    @GET
    public List<DepartamentoDtoRequest> getDepartamentos() {
        return departamentoService.getDepartamentos();
    }

    @GET
    @Path("paginated")
    public PaginatedResponse<DepartamentoDtoRequest> getDepartamentos(@QueryParam("p") @DefaultValue("1") int p, @QueryParam("q") String q) {
        return departamentoService.getDepartamentosP(p, q);
    }

    @GET
    @Path("{id}")
    public DepartamentoDtoRequest getDepartamento(@PathParam("id") long id) {
        return departamentoService.getDepartamento(id);
    }

    @POST
    @Transactional
    public Response create(@Valid @ConvertGroup(to = DepartamentoDtoRequest.OnCreate.class) DepartamentoDtoRequest dto) {
        return departamentoService.insert(dto);
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response update(@PathParam("id") long id, @Valid DepartamentoDtoRequest dto) {
        return departamentoService.update(id, dto);
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
        return departamentoService.delete(id);
    }

    @GET
    @Path("reporte")
    @Produces({"application/pdf",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
    public Response generarReporte(@QueryParam("format") @DefaultValue("pdf") String format) throws JRException, SQLException {
        return departamentoService.generarReporte(format);
    }
}
