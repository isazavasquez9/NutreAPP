package com.example.NutreAPP.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.models.Usuario;
import com.example.NutreAPP.repositories.IUsuarioRepositorio;

@Component
public class UsuarioValidacion {

    @Autowired
    private IUsuarioRepositorio repositorio;

    //VALIDACIONES DE FORMATO

    //1. validar que un campo se obligatorio
    public void validarTextoObligatorio(String valor, String campo){
    if(valor == null || valor.isBlank()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"el campo"+campo+"es obligatorio");
    }
}
    //2. validar la longitud de un nombre

    public void validarNombre(String nombre){
        if (nombre.trim().length()<3 ||nombre.trim().length()>100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"el nomrbe debe tener entre 3 y 100 caracteres");
        }
    }
    //3.expresiones regulares para verificar que un correo si sea correo 

    public void validarCorreo(String correo){
        if (!correo.matches("^[\\w.+-]+@[\\w-]+\\.[\\w.]+$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"el correo no cumple con el formato esperado");
        }

    }

    //validar que la contraseña tenga minimo 8 caracteres

    public void validarContraseña(String contraseña){
        if (contraseña.length()<8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"la contraseña debe tener minimo 8 caractes");
            
        }

    }

    //VALIDACIONES DEL NEGOCIO 

    public void validarUsuarioNuevo(Usuario datos){
        String correoEntregado=datos.getCorreo();
        if(this.repositorio.exiexistsByCorreo(correoEntregado)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"el correo entregado ya existe en la base de datos");

        }
    }

}
