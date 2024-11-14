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
    estado BOOLEAN
    );

CREATE TABLE IF NOT EXISTS alerta (
                                      id_alerta SERIAL PRIMARY KEY,
                                      id_tarea INTEGER REFERENCES tarea(id_tarea)
    );