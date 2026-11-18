package com.example.NutreAPP.services;
import java.util.List;
import java.util.UUID;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.example.NutreAPP.models.Video;
import com.example.NutreAPP.repositories.IVideosRepositorio;

public class VideoServicio {

 @Autowired
private IVideosRepositorio repositorio;
//metodo para guardar un usuario
public Video guardar(Video datos){
    return  this.repositorio.save(datos);
}
public Video buscarPorId(UUID id){
    Optional<Video> usuarioBuscado = this.repositorio.findById(id);
    if(!usuarioBuscado.isPresent()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no encontrado");
    }else{
        return usuarioBuscado.get();
    }
}
public List<Video> buscarTodos(){
    this.repositorio.findAll();

}

}
