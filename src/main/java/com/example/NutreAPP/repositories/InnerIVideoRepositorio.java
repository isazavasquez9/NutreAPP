package com.example.NutreAPP.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.NutreAPP.models.Categoria;
import com.example.NutreAPP.models.Video;

@Repository
public interface InnerIVideoRepositorio extends JpaRepository<Video, UUID> {

    List<Video> findByCategoria(Categoria categoria);

    List<Video> findByTituloIgnoreCase(String titulo);

    List<Video> findByDescripcionContainingIgnoreCase(String descripcion);

    List<Video> findByUrl(String url);

    List<Video> findByTituloIgnoreCaseAndCategoria(String titulo, Categoria categoria);
}
