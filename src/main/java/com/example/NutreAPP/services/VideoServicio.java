package com.example.NutreAPP.services;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.NutreAPP.models.Video;
import com.example.NutreAPP.repositories.IVideosRepositorio;

public class VideoServicio {

 @Autowired
private IVideosRepositorio repositorio;
//metodo para guardar un usuario
public Video guardar(Video datos){
    return  this.repositorio.save(datos);
}

}
