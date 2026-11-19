package com.example.NutreAPP.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.NutreAPP.models.Categoria;
import com.example.NutreAPP.models.Video;

@Repository
public interface IVideosRepositorio extends JpaRepository<Video, UUID> {

    List<Video> findByCategoria(Categoria categoria);

    List<Video> findByTituloIgnoreCase(String titulo);

    List<Video> findByDescripcionContainingIgnoreCase(String descripcion);

    List<Video> findByUrl(String url);

    boolean existsByUrl(String url);

    @Query(value = "SELECT COUNT(*) FROM video WHERE url = :url AND id <> :id", nativeQuery = true)
    long contarUrlEnOtroVideo(@Param("url") String url, @Param("id") UUID id);

    List<Video> findByTituloIgnoreCaseAndCategoria(String titulo, Categoria categoria);
}
