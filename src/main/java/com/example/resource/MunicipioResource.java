package com.example.resource;

import com.example.dto.MunicipioDto;
import com.example.service.interfaces.MunicipioService;
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

@Path("municipios")
public class MunicipioResource {

    @Inject
    MunicipioService municipioService;
    @GET
    public List<MunicipioDto> getMunicipios(){
        return municipioService.getMunicipios();
    }

    @GET
    @Path("paginated")
    public PaginatedResponse<MunicipioDto> getMunicipios(@QueryParam("p") @DefaultValue("1") int p, @QueryParam("q") String q){
        return municipioService.getMunicipiosP(p,q);
    }

    @GET
    @Path("{id}")
    public MunicipioDto getMunicipio(@PathParam("id") long id){
        return municipioService.getMunicipio(id);
    }

    @POST
    @Transactional
    public Response create(@Valid @ConvertGroup(to= MunicipioDto.OnCreate.class) MunicipioDto dto){
        return municipioService.insert(dto);
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response update(@PathParam("id") long id, @Valid MunicipioDto dto){
        return municipioService.update(id,dto);
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response delete(@PathParam("id") long id){
        return municipioService.delete(id);
    }

    @GET
    @Path("reporte")
    @Produces({"application/pdf",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
    public Response generarReporte(@QueryParam("format") @DefaultValue("pdf") String format) throws JRException, SQLException {
        return municipioService.generarReporte(format);
    }
}
