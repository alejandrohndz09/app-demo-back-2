package com.example.service.interfaces;

import com.example.dto.DistritoDtoDetail;
import com.example.dto.DistritoDtoRequest;
import com.example.tools.PaginatedResponse;
import jakarta.ws.rs.core.Response;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;

public interface DistritoService {
    public List<DistritoDtoDetail> getDistritos();
    public PaginatedResponse<DistritoDtoDetail> getDistritosP(int page, String q);
    public DistritoDtoDetail getDistrito(long id);
    public Response insert(DistritoDtoRequest departamento);
    public Response update(long id, DistritoDtoRequest dto);
    public Response delete(long id);
    public Response generarReporte(String format) throws JRException, SQLException ;
}
