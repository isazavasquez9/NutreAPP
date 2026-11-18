package com.example.NutreAPP.services;
import java.util.List;
import java.util.UUID;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.models.Usuario;
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
    return  this.repositorio.findAll();

}
public Video modificar(UUID id, Video datos){
    Optional<Video> usuarioBuscado = this.repositorio.findById(id);
    if(!usuarioBuscado.isPresent()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no encontrado");
    }else{
        //1.PRECARGAR AL INFO QUE EXISTE ACTUALMENTE EN BD
        Video usuarioEncontrado=usuarioBuscado.get();
        
        //2.ESPACIO APRA MODIFICAR SOLO LAS PROPIEDADES PERMITIDAS
        usuarioEncontrado.setTitulo(datos.getTitulo());
        

        //3.ESPACIO PARA VOLVER A GUARDAR EL USUARIO QUE SE ACABA DE MODIFICAR 
        return this.repositorio.save(usuarioEncontrado);

    }
}
public boolean eliminar(UUID id){
     Optional<Video> usuarioBuscado = this.repositorio.findById(id);
    if(!usuarioBuscado.isPresent()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no encontrado");
    }else{
        this.repositorio.deleteById(id);
        return true;
    }
}


}
