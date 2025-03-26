package com.example.service.interfaces;

import com.example.dto.MunicipioDto;
import com.example.tools.PaginatedResponse;
import jakarta.ws.rs.core.Response;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;

public interface MunicipioService {
    public List<MunicipioDto> getMunicipios();
    public PaginatedResponse<MunicipioDto> getMunicipiosP(int page, String q);
    public MunicipioDto getMunicipio(long id);
    public Response insert(MunicipioDto departamento);
    public Response update(long id, MunicipioDto dto);
    public Response delete(long id);
    public Response generarReporte(String format) throws JRException, SQLException ;
}
