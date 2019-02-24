package com.groupoffive.listapp.controllers;

import com.groupoffive.listapp.exceptions.IncorrectEmailOrPasswordException;
import com.groupoffive.listapp.models.Usuario;
import com.groupoffive.listapp.util.Crypt;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UsersController {

    private EntityManager entityManager;
    private Crypt crypt;

    public UsersController(EntityManager entityManager, Crypt crypt) {
        this.entityManager = entityManager;
        this.crypt         = crypt;
    }

    /**
     * Valida o e-mail e senha informados e devolve os dados do usuário correspondente.
     * @param email
     * @param senha
     * @return
     * @throws IncorrectEmailOrPasswordException
     */
    public Usuario login(String email, String senha) throws IncorrectEmailOrPasswordException {
        String cryptedPass = crypt.cryptString(senha);

        try {
            return entityManager.createQuery(
                    "SELECT u from Usuario u WHERE u.email = :email AND u.senha = :senha", Usuario.class
            ).setParameter("email", email).setParameter("senha", cryptedPass).getSingleResult();
        } catch (NoResultException e) {
            throw new IncorrectEmailOrPasswordException();
        }
    }

}
