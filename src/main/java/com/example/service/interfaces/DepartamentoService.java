package com.example.service.interfaces;

import com.example.dto.DepartamentoDtoDetail;
import com.example.dto.DepartamentoDtoRequest;
import com.example.tools.PaginatedResponse;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface DepartamentoService {
    public List<DepartamentoDtoDetail> getDepartamentos();
    public PaginatedResponse<DepartamentoDtoDetail> getDepartamentosP(int page, String q);
    public DepartamentoDtoDetail getDepartamento(long id);
    public Response insert(DepartamentoDtoRequest departamento);
    public Response update(long id, DepartamentoDtoRequest dto);
    public Response delete(long id);
}
