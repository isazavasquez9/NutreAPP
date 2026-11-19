package com.example.NutreAPP.validations;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.repositories.IUsuarioRepositorio;
import com.example.NutreAPP.repositories.IVideosRepositorio;

@Component
public class VisualizacionValidacion {

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    private IVideosRepositorio videoRepositorio;

    public void validarFechaObligatoria(LocalDate fecha) {
        if (fecha == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "la fecha de visualizacion es obligatoria");
        }
    }

    public void validarFechaNoFutura(LocalDate fecha) {
        if (fecha != null && fecha.isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "la fecha de visualizacion no puede ser futura");
        }
    }

    public void validarCompletado(Boolean completado) {
        if (completado == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "el campo completado es obligatorio");
        }
    }

    public void validarUsuarioYVideoExistentes(UUID usuarioId, UUID videoId) {
        if (usuarioId == null || !this.usuarioRepositorio.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "el usuario no existe");
        }
        if (videoId == null || !this.videoRepositorio.existsById(videoId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "el video no existe");
        }
    }
}
