package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    //ACA GENERO EL INICIO DE SESION.
    public String login(@RequestBody Usuario usuario){

        Usuario usuarioLogueado= usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if (usuarioLogueado != null){

            //genero el token jwt
           String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
            return tokenJwt;
        }
        return "FAIL";
    }
//@RequestMapping(value = "api/login", method = RequestMethod.POST)
//public String login(@RequestBody Usuario usuario){
//
//    Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
//    if (usuarioLogueado != null){
//
//        // Generar el token JWT
//        String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()) + usuarioLogueado.getEmail());
//        return tokenJwt;
//    }
//    return "FAIL";
//}
}
