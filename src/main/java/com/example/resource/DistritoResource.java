package com.example.resource;

import com.example.dto.DistritoDto;
import com.example.service.interfaces.DistritoService;
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

@Path("distritos")
public class DistritoResource {

    @Inject
    DistritoService distritoService;
    @GET
    public List<DistritoDto> getDistritos(){
        return distritoService.getDistritos();
    }

    @GET
    @Path("paginated")
    public PaginatedResponse<DistritoDto> getDistritos(@QueryParam("p") @DefaultValue("1") int p, @QueryParam("q") String q){
        return distritoService.getDistritosP(p,q);
    }

    @GET
    @Path("{id}")
    public DistritoDto getDistrito(@PathParam("id") long id){
        return distritoService.getDistrito(id);
    }

    @POST
    @Transactional
    public Response create(@Valid @ConvertGroup(to = DistritoDto.OnCreate.class) DistritoDto dto){
        return distritoService.insert(dto);
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response update(@PathParam("id") long id, @Valid DistritoDto dto){
        return distritoService.update(id,dto);
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response delete(@PathParam("id") long id){
        return distritoService.delete(id);
    }

    @GET
    @Path("reporte")
    @Produces({"application/pdf",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
    public Response generarReporte(@QueryParam("format") @DefaultValue("pdf") String format) throws JRException, SQLException {
        return distritoService.generarReporte(format);
    }
}
