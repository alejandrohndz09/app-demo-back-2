package com.example.service.interfaces;

import com.example.dto.DepartamentoDto;
import com.example.tools.PaginatedResponse;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface DepartamentoService {
    public List<DepartamentoDto> getDepartamentos();
    public PaginatedResponse<DepartamentoDto> getDepartamentosP(int page, String q);
    public DepartamentoDto getDepartamento(long id);
    public Response insert(DepartamentoDto departamento);
    public Response update(long id, DepartamentoDto dto);
    public Response delete(long id);
}
