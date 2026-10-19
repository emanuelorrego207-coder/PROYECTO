package com.mantenimiento.domain.port;

import com.mantenimiento.domain.model.SolicitudMantenimiento;
import java.util.List;

public interface SolicitudRepository {

    SolicitudMantenimiento guardar(SolicitudMantenimiento solicitud);

    SolicitudMantenimiento buscarPorId(Long id);

    List<SolicitudMantenimiento> listar();
}
