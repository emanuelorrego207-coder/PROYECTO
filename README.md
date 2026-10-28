# Caso 2 – Sistema de gestión de mantenimiento de equipos

**Integrantes:** Jeronimo Velasquez · Juan Andres Rios · Emanuel Orrego
**Curso:** Programación Orientada a Objetos

Aplicación Spring Boot con **Arquitectura Limpia / Hexagonal** para reportar fallas de equipos, asignar técnicos, finalizar mantenimientos y generar reportes.

## Tecnologías

- Java 17+
- Spring Boot 3.3.5 (Spring Web, Spring Data JPA)
- Base de datos H2 (en memoria)
- Gradle

## Estructura del proyecto

```
com.mantenimiento
├── MantenimientoApplication.java
├── domain
│   ├── model        → Usuario, Tecnico, Equipo, Reportable, EstadoSolicitud, SolicitudMantenimiento
│   └── port         → SolicitudRepository (puerto de salida)
├── application
│   └── service      → SolicitudService (casos de uso)
└── infrastructure
    ├── controller   → SolicitudController, ReportarFallaRequest, AsignarTecnicoRequest, ManejadorErrores
    ├── persistence  → SolicitudEntity, SpringDataSolicitudRepository, SolicitudRepositoryAdapter
    └── config       → DatosIniciales (datos de ejemplo)
```

| Capa | Responsabilidad |
|------|-----------------|
| Dominio | Objetos y reglas del negocio. Java puro, sin Spring. |
| Aplicación | Coordina los casos de uso. |
| Puerto | Interfaz con lo que la aplicación necesita (guardar, buscar, listar). |
| Infraestructura | Spring, REST, JPA y H2. |

## Reglas de negocio

- Al reportar una falla, el equipo queda **fuera de servicio**.
- La descripción de la falla es obligatoria.
- No se puede asignar técnico a una solicitud **finalizada**.
- No se puede finalizar una solicitud **sin técnico asignado**.
- Una solicitud no se puede finalizar **dos veces**.
- Al finalizar, el equipo vuelve a estar **en servicio**.

## Cómo ejecutar

### Desde IntelliJ IDEA
1. `File > Open` y seleccionar la carpeta del proyecto.
2. Esperar a que Gradle descargue las dependencias.
3. Ejecutar `MantenimientoApplication.java` (triángulo verde).

### Desde la terminal
```bash
# Windows
gradlew.bat bootRun

# Mac / Linux
./gradlew bootRun
```

La aplicación queda disponible en **http://localhost:8081**.

> El puerto se configura con `server.port` en `src/main/resources/application.properties`. Si aparece *"Port 8081 was already in use"*, detener la ejecución anterior o cambiar ese valor.

## Direcciones

| Qué | URL |
|-----|-----|
| Lista de solicitudes | http://localhost:8081/api/solicitudes |
| Reporte de la solicitud 1 | http://localhost:8081/api/solicitudes/1/reporte |
| Consola H2 | http://localhost:8081/h2-console |

**Consola H2:** JDBC URL `jdbc:h2:mem:mantenimientodb` · Usuario `sa` · Contraseña vacía.

## Endpoints de la API

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/api/solicitudes` | Reportar una falla |
| GET | `/api/solicitudes` | Listar solicitudes |
| PUT | `/api/solicitudes/{id}/tecnico` | Asignar técnico |
| PUT | `/api/solicitudes/{id}/finalizar` | Finalizar mantenimiento |
| GET | `/api/solicitudes/{id}/reporte` | Generar reporte |

### Ejemplos (Postman)

**Reportar falla**
```http
POST http://localhost:8081/api/solicitudes
Content-Type: application/json

{
  "equipoId": 1,
  "equipoNombre": "Computador Dell",
  "ubicacion": "Laboratorio de Sistemas",
  "descripcion": "El computador no enciende"
}
```

**Asignar técnico**
```http
PUT http://localhost:8081/api/solicitudes/3/tecnico
Content-Type: application/json

{
  "tecnicoId": 101,
  "nombre": "Juan Ríos",
  "especialidad": "Hardware"
}
```

**Finalizar**
```http
PUT http://localhost:8081/api/solicitudes/3/finalizar
```

**Reporte**
```http
GET http://localhost:8081/api/solicitudes/3/reporte
```

Si se rompe una regla de negocio, la API responde **400** con el mensaje del error, por ejemplo `No se puede finalizar sin técnico asignado`. Si la solicitud no existe, responde **404**.

## Pruebas

```bash
./gradlew test
```

## Datos de ejemplo

Al arrancar, `DatosIniciales` crea dos solicitudes (Computador Acer y Teclado Redragon) con sus técnicos asignados. Como H2 está en memoria, los datos se reinician cada vez que se ejecuta la aplicación.

## Recorrido de una petición

```
HTTP → SolicitudController → SolicitudService → SolicitudRepository (puerto)
     → SolicitudRepositoryAdapter → SpringDataSolicitudRepository → JPA → H2
```
