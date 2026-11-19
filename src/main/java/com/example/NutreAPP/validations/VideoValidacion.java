package com.example.NutreAPP.validations;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.models.Categoria;
import com.example.NutreAPP.models.Video;
import com.example.NutreAPP.repositories.IVideosRepositorio;

@Component
public class VideoValidacion {

    @Autowired
    private IVideosRepositorio repositorio;

    public void validarTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()
                || titulo.trim().length() < 5 || titulo.trim().length() > 120) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "el titulo debe tener entre 5 y 120 caracteres");
        }
    }

    public void validarUrl(String url) {
        if (url == null || url.isBlank()
                || (!url.startsWith("http://") && !url.startsWith("https://"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "la URL es obligatoria y debe empezar por http:// o https://");
        }
    }

    public void validarCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "la categoria es obligatoria");
        }
    }

    public void validarDescripcion(String descripcion) {
        if (descripcion != null && descripcion.length() > 500) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "la descripcion no puede superar 500 caracteres");
        }
    }

    public void validarFormato(Video datos) {
        validarTitulo(datos.getTitulo());
        validarUrl(datos.getUrl());
        validarCategoria(datos.getCategoria());
        validarDescripcion(datos.getDescripcion());
    }

    public void validarVideoNuevo(Video datos) {
        if (this.repositorio.existsByUrl(datos.getUrl())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "ya existe un video con esa URL");
        }
    }

    public void validarUrlEnModificacion(String url, UUID id) {
        if (this.repositorio.contarUrlEnOtroVideo(url, id) > 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "ya existe un video con esa URL");
        }
    }
}
