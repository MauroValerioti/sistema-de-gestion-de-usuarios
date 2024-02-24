package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class usuarioController {

    @Autowired
    UsuarioDao usuarioDao;

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(){

        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){

        //metodo para hashear el password.
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());

        //usuarioDao.getUsuario().setPassword(hash);
        //usuarioDao.registrar(hash);

        // Actualizar la contraseña con el hash.
        usuario.setPassword(hash);

        // Registrar el usuario en la base de datos.
        usuarioDao.registrar(usuario);

    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){

        return  new Usuario(id, "Mauro", "Valerioti", "mauro@gmai.com", "2494468713", "Pass123");

    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void deleteUsuario(@PathVariable long id){

        usuarioDao.delete(id);
    }
}
