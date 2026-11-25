# Frontend React – Solicitudes de mantenimiento

Pantalla React (Vite) que consume el endpoint `GET http://localhost:8081/api/solicitudes`
del backend Spring Boot y muestra las solicitudes en tarjetas.

Patrón: endpoint → `fetch` → `useState` → `map()` → interfaz.

## Cómo ejecutarlo

1. Iniciar el backend Spring Boot (puerto 8081).
2. En esta carpeta:

```bash
npm install
npm run dev
```

3. Abrir http://localhost:5173

En Windows también se puede dar doble clic en `iniciar-frontend.bat`.
