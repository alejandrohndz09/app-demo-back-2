package com.example.service.interfaces;

import com.example.dto.MunicipioDto;
import com.example.dto.MunicipioDtoDetail;
import com.example.dto.MunicipioDtoRequest;
import com.example.tools.PaginatedResponse;
import jakarta.ws.rs.core.Response;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;

public interface MunicipioService {
    public List<MunicipioDtoRequest> getMunicipios();
    public PaginatedResponse<MunicipioDtoRequest> getMunicipiosP(int page, String q);
    public MunicipioDtoRequest getMunicipio(long id);
    public Response insert(MunicipioDtoRequest departamento);
    public Response update(long id, MunicipioDtoRequest dto);
    public Response delete(long id);
    public Response generarReporte(String format) throws JRException, SQLException ;
}
