package com.control2_backend.repository;

import com.control2_backend.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class UsuarioRepository {

    @Autowired
    private Sql2o sql2o;

    public List<UsuarioEntity> findAll() {
        String sql = "SELECT id_usuario, nombre, clave FROM usuario";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(UsuarioEntity.class);
        }
    }

    public void save(UsuarioEntity entity) {
        String sql = "INSERT INTO usuario (nombre, clave) VALUES (:nombre, :clave)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("nombre", entity.getNombre())
                    .addParameter("clave", entity.getClave())
                    .executeUpdate();
        }
    }

    public UsuarioEntity findById(Long id) {
        String sql = "SELECT id_usuario, nombre, clave FROM usuario WHERE id_usuario = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(UsuarioEntity.class);
        }
    }

    public UsuarioEntity findByName(String nombre){
        String sql = "SELECT id_usuario, nombre, clave FROM usuario WHERE nombre = :nombre";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("nombre", nombre)
                    .executeAndFetchFirst(UsuarioEntity.class);
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM usuario WHERE id_usuario = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
