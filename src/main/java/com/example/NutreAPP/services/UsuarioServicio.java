package com.example.NutreAPP.services;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.example.NutreAPP.models.Usuario;
import com.example.NutreAPP.repositories.IUsuarioRepositorio;

public class UsuarioServicio {
// para programar los servicios necesito los repositorios, se llama inyeccion de dependencias
@Autowired
private IUsuarioRepositorio repositorio;
//metodo para guardar un usuario
public Usuario guardar(Usuario datos){
    return  this.repositorio.save(datos);
}
// metodo para buscar por id
public Usuario buscarPorId(UUID id){
    Optional<Usuario> usuarioBuscado = this.repositorio.findById(id);
    if(!usuarioBuscado.isPresent()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no encontrado");
    }else{
        return usuarioBuscado.get();
    }
}
//metodo para buscar todos
public List<Usuario> buscarTodos(){
    this.repositorio.findAll();

}


//metodo para modificar 
//metodo para eliminar
//metodo para buscar por departamento y rol
//metodo para buscar por correo
//metodo para buscar por rol
//metodo para verificar correo
}
