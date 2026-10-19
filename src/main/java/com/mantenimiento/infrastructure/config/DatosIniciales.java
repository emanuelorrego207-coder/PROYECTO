package com.mantenimiento.infrastructure.config;

import com.mantenimiento.application.service.SolicitudService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatosIniciales {

    @Bean
    CommandLineRunner cargarDatos(SolicitudService service) {
        return args -> {
            service.reportarFalla(2L, "Computador Acer", "Laboratorio de Sistemas",
                    "El computador no tiene Windows.");
            service.reportarFalla(3L, "Teclado Redragon", "Laboratorio de Sistemas",
                    "El teclado no teclea.");

            service.asignarTecnico(1L, 102L, "Emanuel Orrego", "Software");
            service.asignarTecnico(2L, 103L, "Jeronimo Velasquez", "QA");

            System.out.println(service.generarReporte(1L));
            System.out.println(service.generarReporte(2L));
        };
    }
}
