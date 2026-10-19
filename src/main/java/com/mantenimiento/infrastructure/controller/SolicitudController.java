package com.mantenimiento.infrastructure.controller;

import com.mantenimiento.application.service.SolicitudService;
import com.mantenimiento.domain.model.SolicitudMantenimiento;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final SolicitudService service;

    public SolicitudController(SolicitudService service) {
        this.service = service;
    }

    @PostMapping
    public SolicitudMantenimiento reportarFalla(@RequestBody ReportarFallaRequest request) {
        return service.reportarFalla(
                request.equipoId(),
                request.equipoNombre(),
                request.ubicacion(),
                request.descripcion());
    }

    @GetMapping
    public List<SolicitudMantenimiento> listar() {
        return service.listar();
    }

    @PutMapping("/{id}/tecnico")
    public SolicitudMantenimiento asignarTecnico(@PathVariable Long id,
                                                 @RequestBody AsignarTecnicoRequest request) {
        return service.asignarTecnico(id, request.tecnicoId(), request.nombre(), request.especialidad());
    }

    @PutMapping("/{id}/finalizar")
    public SolicitudMantenimiento finalizar(@PathVariable Long id) {
        return service.finalizar(id);
    }

    @GetMapping("/{id}/reporte")
    public String generarReporte(@PathVariable Long id) {
        return service.generarReporte(id);
    }
}
