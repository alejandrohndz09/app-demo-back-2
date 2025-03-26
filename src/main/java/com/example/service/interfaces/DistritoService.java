package com.example.service.interfaces;

import com.example.dto.DistritoDto;
import com.example.tools.PaginatedResponse;
import jakarta.ws.rs.core.Response;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;

public interface DistritoService {
    public List<DistritoDto> getDistritos();
    public PaginatedResponse<DistritoDto> getDistritosP(int page, String q);
    public DistritoDto getDistrito(long id);
    public Response insert(DistritoDto departamento);
    public Response update(long id, DistritoDto dto);
    public Response delete(long id);
    public Response generarReporte(String format) throws JRException, SQLException ;
}
