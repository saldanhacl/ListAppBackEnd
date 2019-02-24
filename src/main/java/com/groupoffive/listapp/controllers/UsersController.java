package com.groupoffive.listapp.controllers;

import com.groupoffive.listapp.exceptions.EmailAlreadyInUseException;
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

    /**
     * Adiciona um novo usuário com o nome, e-mail e senha informados.
     * @param nome
     * @param email
     * @param senha
     * @return
     * @throws EmailAlreadyInUseException
     */
    public Usuario addUser(String nome, String email, String senha) throws EmailAlreadyInUseException {
        String cryptedPass = crypt.cryptString(senha);

        Usuario usuario    = new Usuario(nome, email, cryptedPass);

        if (this.emailIsInUse(email)) throw new EmailAlreadyInUseException();

        entityManager.getTransaction().begin();
        entityManager.persist(usuario);
        entityManager.getTransaction().commit();

        return usuario;
    }

    /**
     * Verifica se o e-mail informado já está em uso por algum usuário.
     * @param email
     * @return
     */
    private boolean emailIsInUse(String email) {
        try {
            Usuario usuario = entityManager.createQuery(
                    "SELECT u from Usuario u WHERE u.email = :email", Usuario.class
            ).setParameter("email", email).getSingleResult();

            return null != usuario;
        } catch (NoResultException e) {
            return false;
        }
    }

}
