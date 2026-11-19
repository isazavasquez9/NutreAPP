package com.example.NutreAPP.services;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.models.Usuario;
import com.example.NutreAPP.repositories.IUsuarioRepositorio;
import com.example.NutreAPP.validations.UsuarioValidacion;
@Service
public class UsuarioServicio {
// para programar los servicios necesito los repositorios, se llama inyeccion de dependencias
    @Autowired
    private IUsuarioRepositorio repositorio;

    @Autowired
    private UsuarioValidacion validador;



    //metodo para guardar un usuario
    public Usuario guardar(Usuario datos){
        this.validador.validarCorreo(datos.getCorreo());
        this.validador.validarUsuarioNuevo(datos);
        this.validador.validarNombre(datos.getNombre());
        this.validador.validarContraseña(datos.getContrasena_hash());
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
    return  this.repositorio.findAll();
}
//metodo para modificar 
    public Usuario modificar(UUID id, Usuario datos){
    Optional<Usuario> usuarioBuscado = this.repositorio.findById(id);
        if(!usuarioBuscado.isPresent()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no encontrado");
        }else{
        //1.PRECARGAR AL INFO QUE EXISTE ACTUALMENTE EN BD
        Usuario usuarioEncontrado=usuarioBuscado.get();

        //2.ESPACIO APRA MODIFICAR SOLO LAS PROPIEDADES PERMITIDAS
        usuarioEncontrado.setNombre(datos.getNombre());
        usuarioEncontrado.setRol(datos.getRol());

        //3.ESPACIO PARA VOLVER A GUARDAR EL USUARIO QUE SE ACABA DE MODIFICAR 
        return this.repositorio.save(usuarioEncontrado);

    }
}
//metodo para eliminar
public boolean eliminar(UUID id){
     Optional<Usuario> usuarioBuscado = this.repositorio.findById(id);
    if(!usuarioBuscado.isPresent()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no encontrado");
    }else{
        this.repositorio.deleteById(id);
        return true;
    }
}

//metodo para buscar por departamento y rol
//metodo para buscar por correo
//metodo para buscar por rol
//metodo para verificar correo
}
