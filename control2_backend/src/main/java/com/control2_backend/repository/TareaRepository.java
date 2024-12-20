package com.control2_backend.repository;

import com.control2_backend.entity.TareaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class TareaRepository {

    @Autowired
    private Sql2o sql2o;

    public List<TareaEntity> findAll() {
        String sql = "SELECT id_tarea, id_usuario, nombre, descripcion, fecha_vencimiento, estado FROM tarea";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(TareaEntity.class);
        }
    }

    public void save(TareaEntity entity) {
        String sql = "INSERT INTO tarea (id_usuario, nombre, descripcion, fecha_vencimiento, estado) VALUES (:id_usuario, :nombre, :descripcion, :fecha_vencimiento, :estado)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id_usuario", entity.getId_usuario())
                    .addParameter("nombre", entity.getNombre())
                    .addParameter("descripcion", entity.getDescripcion())
                    .addParameter("fecha_vencimiento", entity.getFecha_vencimiento())
                    .addParameter("estado", entity.getEstado())
                    .executeUpdate();
        }
    }

    public TareaEntity findById(Long id) {
        String sql = "SELECT id_usuario, nombre, descripcion, fecha_vencimiento, estado FROM tarea WHERE id_tarea = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(TareaEntity.class);
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM tarea WHERE id_tarea = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public List<TareaEntity> findByEstadoAndKeyword(String estado, String keyword) {
        StringBuilder sql = new StringBuilder("SELECT id_tarea, id_usuario, nombre, descripcion, fecha_vencimiento, estado FROM tarea WHERE 1=1");

        if (estado != null && !estado.isEmpty()) {
            sql.append(" AND estado = :estado");
        }

        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND (LOWER(nombre) LIKE :keyword OR LOWER(descripcion) LIKE :keyword)");
        }

        try (Connection con = sql2o.open()) {
            var query = con.createQuery(sql.toString());

            if (estado != null && !estado.isEmpty()) {
                query.addParameter("estado", estado);
            }

            if (keyword != null && !keyword.isEmpty()) {
                query.addParameter("keyword", "%" + keyword.toLowerCase() + "%");
            }

            List<TareaEntity> tareas = query.executeAndFetch(TareaEntity.class);

            return tareas;
        }
    }
}

