package com.control2_backend.repository;

import com.control2_backend.entity.AlertaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class AlertaRepository {

    @Autowired
    private Sql2o sql2o;

    public List<AlertaEntity> findAll() {
        String sql = "SELECT id_alerta, id_tarea FROM alerta";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(AlertaEntity.class);
        }
    }

    public void save(AlertaEntity entity) {
        String sql = "INSERT INTO alerta (id_tarea) VALUES (:id_tarea)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id_tarea", entity.getId_tarea())
                    .executeUpdate();
        }
    }

    public AlertaEntity findById(Long id) {
        String sql = "SELECT id_tarea FROM alerta WHERE id_alerta = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(AlertaEntity.class);
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM alerta WHERE id_alerta = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
