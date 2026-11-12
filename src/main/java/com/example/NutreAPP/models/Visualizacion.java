package com.example.NutreAPP.models;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Visualizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public Visualizacion(UUID id) {
        this.id = id;
    }

    @Column(name = "fecha_visualizacion", nullable = false)
    private LocalDate fechaVisualizacion;

    @Column(name = "completado", nullable = false)
    private Boolean completado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference(value = "usuario-visualizacion")
    private Usuario usuario;

    public Visualizacion() {
    }

    public Visualizacion(UUID id, LocalDate fecha_visualizacion, Boolean completado) {
        this.id = id;
        this.fechaVisualizacion = fecha_visualizacion;
        this.completado = completado;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getFecha_visualizacion() {
        return fechaVisualizacion;
    }

    public void setFecha_visualizacion(LocalDate fecha_visualizacion) {
        this.fechaVisualizacion = fecha_visualizacion;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }

}

