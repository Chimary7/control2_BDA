CREATE TABLE IF NOT EXISTS usuario (
                                       id_usuario SERIAL PRIMARY KEY,
                                       nombre VARCHAR(50),
    clave VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS tarea (
                                     id_tarea SERIAL PRIMARY KEY,
                                     id_usuario INTEGER REFERENCES usuario(id_usuario),
    nombre VARCHAR(50),
    descripcion VARCHAR(100),
    fecha_vencimiento TIMESTAMP,
    estado Varchar(20)
    );

CREATE OR REPLACE FUNCTION verificar_tareas_por_vencer(usuario_id BIGINT)
RETURNS TABLE (
    id_tarea BIGINT,
    id_usuario BIGINT,
    nombre VARCHAR,
    descripcion VARCHAR,
    fecha_vencimiento TIMESTAMP,
    estado VARCHAR
) AS $BODY$
BEGIN
    -- Actualizar tareas vencidas
UPDATE tarea t1
SET estado = 'vencido'
WHERE t1.id_usuario = usuario_id
  AND t1.estado IN ('pendiente', 'próximo a vencer')
  AND t1.fecha_vencimiento <= CURRENT_TIMESTAMP;

-- Actualizar el estado de las tareas próximas a vencer
UPDATE tarea t2
SET estado = 'próximo a vencer'
WHERE t2.id_usuario = usuario_id
  AND t2.estado = 'pendiente'
  AND t2.fecha_vencimiento <= (CURRENT_TIMESTAMP + INTERVAL '24 hours')
  AND t2.fecha_vencimiento > CURRENT_TIMESTAMP;

-- Retornar solo las tareas próximas a vencer
RETURN QUERY
SELECT
    t3.id_tarea::BIGINT,
        t3.id_usuario::BIGINT,
        t3.nombre,
    t3.descripcion,
    t3.fecha_vencimiento,
    t3.estado
FROM tarea t3
WHERE t3.id_usuario = usuario_id
  AND t3.estado = 'proximo_a_vencer'
ORDER BY t3.fecha_vencimiento;
END;
$BODY$
LANGUAGE plpgsql;
/
