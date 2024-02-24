package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImpl implements UsuarioDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Usuario getUsuario() {
        return null;
    }

    @Override
    public void delete(long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.persist(usuario);
    }

    @Override
    public boolean verificarcredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email AND password = :password";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .setParameter("password", usuario.getPassword())
                .getResultList();


        System.out.println("password ingresado: " + usuario.getPassword());
        if (!lista.isEmpty()) {
            System.out.println("password en la DB: " + lista.get(0).getPassword());
        } else {
            System.out.println("La lista está vacía, no se encontró ningún usuario.");
        }


        //returna un boolean segun si encuentra o no un usuario con esas credenciales
        return !lista.isEmpty();
    }

}