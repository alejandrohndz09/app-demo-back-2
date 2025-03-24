package com.example.service.interfaces;

import com.example.dto.DepartamentoDtoDetail;
import com.example.dto.DepartamentoDtoRequest;
import com.example.tools.PaginatedResponse;
import jakarta.ws.rs.core.Response;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;

public interface DepartamentoService {
    public List<DepartamentoDtoRequest> getDepartamentos();
    public PaginatedResponse<DepartamentoDtoRequest> getDepartamentosP(int page, String q);
    public DepartamentoDtoRequest getDepartamento(long id);
    public Response insert(DepartamentoDtoRequest departamento);
    public Response update(long id, DepartamentoDtoRequest dto);
    public Response delete(long id);
    Response generarReporte(String format) throws JRException, SQLException;
}
