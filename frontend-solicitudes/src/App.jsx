import { useEffect, useState } from 'react'
import './App.css'

const ESTADOS = {
  PENDIENTE: 'Pendiente',
  ASIGNADA: 'Asignada',
  FINALIZADA: 'Finalizada',
}

function iniciales(nombre) {
  return nombre
    .split(' ')
    .map(parte => parte[0])
    .slice(0, 2)
    .join('')
    .toUpperCase()
}

function App() {
  const [solicitudes, setSolicitudes] = useState([])
  const [cargando, setCargando] = useState(true)
  const [error, setError] = useState(null)

  useEffect(() => {
    fetch('http://localhost:8081/api/solicitudes')
      .then(respuesta => respuesta.json())
      .then(datos => {
        console.log(datos)
        setSolicitudes(datos)
      })
      .catch(() => setError('No se pudo conectar con el backend. ¿Está Spring Boot corriendo en el puerto 8081?'))
      .finally(() => setCargando(false))
  }, [])

  const contar = estado => solicitudes.filter(s => s.estado === estado).length

  return (
    <div className="app">
      <header className="encabezado">
        <div className="encabezado-contenido">
          <span className="logo">🛠️</span>
          <div>
            <h1>Mantenimiento de Equipos</h1>
            <p>Solicitudes registradas en el sistema</p>
          </div>
        </div>
      </header>

      <main className="contenedor">
        <section className="resumen">
          <div className="resumen-item">
            <span className="resumen-numero">{solicitudes.length}</span>
            <span className="resumen-texto">Total</span>
          </div>
          <div className="resumen-item PENDIENTE">
            <span className="resumen-numero">{contar('PENDIENTE')}</span>
            <span className="resumen-texto">Pendientes</span>
          </div>
          <div className="resumen-item ASIGNADA">
            <span className="resumen-numero">{contar('ASIGNADA')}</span>
            <span className="resumen-texto">Asignadas</span>
          </div>
          <div className="resumen-item FINALIZADA">
            <span className="resumen-numero">{contar('FINALIZADA')}</span>
            <span className="resumen-texto">Finalizadas</span>
          </div>
        </section>

        {cargando && <p className="mensaje">Cargando solicitudes…</p>}
        {error && <p className="mensaje error">{error}</p>}
        {!cargando && !error && solicitudes.length === 0 && (
          <p className="mensaje">No hay solicitudes registradas.</p>
        )}

        <section className="lista">
          {solicitudes.map(solicitud => (
            <article className={`tarjeta ${solicitud.estado}`} key={solicitud.id}>
              <div className="tarjeta-cabecera">
                <span className="numero">#{solicitud.id}</span>
                <span className={`estado ${solicitud.estado}`}>
                  {ESTADOS[solicitud.estado] ?? solicitud.estado}
                </span>
              </div>

              <h2>{solicitud.equipo.nombre}</h2>
              <p className="ubicacion">📍 {solicitud.equipo.ubicacion}</p>

              <blockquote className="falla">{solicitud.descripcion}</blockquote>

              <div className="tarjeta-pie">
                {solicitud.tecnico ? (
                  <div className="tecnico">
                    <span className="avatar">{iniciales(solicitud.tecnico.nombre)}</span>
                    <div>
                      <strong>{solicitud.tecnico.nombre}</strong>
                      <small>{solicitud.tecnico.especialidad}</small>
                    </div>
                  </div>
                ) : (
                  <div className="tecnico sin-tecnico">
                    <span className="avatar">?</span>
                    <div>
                      <strong>Sin técnico</strong>
                      <small>Pendiente de asignar</small>
                    </div>
                  </div>
                )}
                <span className={`equipo-estado ${solicitud.equipo.enServicio ? 'ok' : 'falla-on'}`}>
                  {solicitud.equipo.estado}
                </span>
              </div>
            </article>
          ))}
        </section>
      </main>
    </div>
  )
}

export default App
