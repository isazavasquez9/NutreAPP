package com.example.NutreAPP.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.NutreAPP.models.Usuario;
import com.example.NutreAPP.repositories.IUsuarioRepositorio;

@Service
public class UsuarioServicio {
// para programar los servicios necesito los repositroios, se llama inyeccion de dependencias
@Autowired
private IUsuarioRepositorio repositorio;
//metodo para guardar un usuario
public Usuario guardar(Usuario datos){
    return  this.repositorio.save(datos);
}
// metodo para buscar por id
//metodo para buscar todos
//metodo para modificar 
//metodo para eliminar
//metodo para buscar por departamento y rol
//metodo para buscar por correo
//metodo para buscar por rol
//metodo para verificar correo
}
