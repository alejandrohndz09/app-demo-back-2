package com.example.service;

import com.example.tools.Formatos;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import net.sf.jasperreports.engine.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@ApplicationScoped
public class ReportesService {

    @Inject
    AgroalDataSource dataSource;
    public Response generarReporte(String format,  Map<String, Object> parametros, String nombreReporte) throws JRException, SQLException {

        // Cargar el archivo .jasper desde resources
        InputStream reporteStream = getClass().getResourceAsStream("/"+nombreReporte+".jrxml");
        if (reporteStream == null) {
            throw new NotFoundException("No se encontró el reporte.");
        }

        // Compilar el archivo .jrxml a .jasper
        JasperReport jasperReport = JasperCompileManager.compileReport(reporteStream);

        // Obtener la conexión de la BD desde el pool de Quarkus
        Connection connection = dataSource.getConnection();

        // Llenar el reporte usando la conexión
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, connection);

        // Generar el formato solicitado
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String contentType;
        String extensionFile;

        switch (format.toLowerCase()) {
            case "docx":
                contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
                extensionFile= "docx";
                Formatos.exportToWord(jasperPrint, outputStream);
                break;
            case "xlsx":
                contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                extensionFile="xlsx";
                Formatos.exportToExcel(jasperPrint, outputStream);
                break;
            case "pdf":
            default:
                contentType = "application/pdf";
                extensionFile="pdf";
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                break;
        }
        // Retornar el reporte en el formato seleccionado
        return Response.ok(outputStream.toByteArray(),contentType)
                .header("Content-Disposition",  "attachment; filename=\"reporte."+ extensionFile+"\"")
                .build();
    }
}
