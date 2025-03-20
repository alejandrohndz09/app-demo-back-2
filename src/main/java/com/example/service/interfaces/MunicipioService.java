package com.example.service.interfaces;

import com.example.dto.MunicipioDto;
import com.example.dto.MunicipioDtoDetail;
import com.example.dto.MunicipioDtoRequest;
import com.example.tools.PaginatedResponse;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface MunicipioService {
    public List<MunicipioDtoDetail> getMunicipios();
    public PaginatedResponse<MunicipioDtoDetail> getMunicipiosP(int page, String q);
    public MunicipioDtoDetail getMunicipio(long id);
    public Response insert(MunicipioDtoRequest departamento);
    public Response update(long id, MunicipioDtoRequest dto);
    public Response delete(long id);
}
