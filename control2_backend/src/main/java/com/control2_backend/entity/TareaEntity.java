package com.control2_backend.entity;

import java.sql.Timestamp;

public class TareaEntity {
    private Long id_tarea;
    private Long id_usuario;
    private String nombre;
    private String descripcion;
    private Timestamp fecha_vencimiento;
    private Boolean estado;

    public Long getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(Long id_tarea) {
        this.id_tarea = id_tarea;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Timestamp fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
