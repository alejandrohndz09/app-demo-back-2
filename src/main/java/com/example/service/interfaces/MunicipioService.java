package com.example.service.interfaces;

import com.example.dto.MunicipioDto;
import com.example.tools.PaginatedResponse;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface MunicipioService {
    public List<MunicipioDto> getMunicipios();
    public PaginatedResponse<MunicipioDto> getMunicipiosP(int page, String q);
    public MunicipioDto getMunicipio(long id);
    public Response insert(MunicipioDto departamento);
    public Response update(long id, MunicipioDto dto);
    public Response delete(long id);
}
