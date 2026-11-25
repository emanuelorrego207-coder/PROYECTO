@echo off
title Frontend React - Solicitudes
cd /d "%~dp0"
echo === PASO 3: comprobar Node.js y npm ===
call node -v
call npm -v
echo.
if not exist node_modules (
  echo === Instalando dependencias (npm install) ===
  call npm install
)
echo.
echo === PASO 4: iniciar React (npm run dev) ===
echo Abre http://localhost:5173  (Spring Boot debe estar corriendo en el puerto 8081)
call npm run dev
pause
