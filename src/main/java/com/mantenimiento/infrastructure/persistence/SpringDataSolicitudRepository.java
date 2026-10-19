package com.mantenimiento.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataSolicitudRepository
        extends JpaRepository<SolicitudEntity, Long> {
}
