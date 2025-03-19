package com.example.resource;

import com.example.dto.DistritoDtoDetail;
import com.example.dto.DistritoDtoRequest;
import com.example.service.interfaces.DistritoService;
import com.example.tools.PaginatedResponse;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.groups.ConvertGroup;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("distritos")
public class DistritoResource {

    @Inject
    DistritoService distritoService;
    @GET
    public List<DistritoDtoDetail> getDistritos(){
        return distritoService.getDistritos();
    }

    @GET
    @Path("paginated")
    public PaginatedResponse<DistritoDtoDetail> getDistritos(@QueryParam("p") @DefaultValue("1") int p, @QueryParam("q") String q){
        return distritoService.getDistritosP(p,q);
    }

    @GET
    @Path("{id}")
    public DistritoDtoDetail getDistrito(@PathParam("id") long id){
        return distritoService.getDistrito(id);
    }

    @POST
    @Transactional
    public Response create(@Valid @ConvertGroup(to = DistritoDtoRequest.OnCreate.class) DistritoDtoRequest dto){
        return distritoService.insert(dto);
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response update(@PathParam("id") long id, @Valid DistritoDtoRequest dto){
        return distritoService.update(id,dto);
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response delete(@PathParam("id") long id){
        return distritoService.delete(id);
    }
}
