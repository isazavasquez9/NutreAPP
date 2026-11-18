package com.example.NutreAPP.services;
import java.util.List;
import java.util.UUID;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.example.NutreAPP.models.Visualizacion;
import com.example.NutreAPP.repositories.IVisualizacionRepositorio;

public class VisualizacionServicio {

@Autowired
private IVisualizacionRepositorio repositorio;

public Visualizacion guardar(Visualizacion datos){
    return this.repositorio.save(datos);

}
public Visualizacion buscarPorId(UUID id){
    Optional<Visualizacion> usuarioBuscado = this.repositorio.findById(id);
    if(!usuarioBuscado.isPresent()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no encontrado");
    }else{
        return usuarioBuscado.get();
    }
}
public List<Visualizacion> buscarTodos(){
    this.repositorio.findAll();

}
}
