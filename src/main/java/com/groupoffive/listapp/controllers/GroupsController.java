package com.groupoffive.listapp.controllers;

import com.groupoffive.listapp.exceptions.UserNotFoundException;
import com.groupoffive.listapp.models.GrupoDeUsuarios;
import com.groupoffive.listapp.models.Usuario;

import javax.persistence.EntityManager;
import java.util.Set;

public class GroupsController {

    private EntityManager entityManager;

    public GroupsController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Retorna os grupos aos quais um usuário está associado.
     * @param userId
     * @return
     * @throws UserNotFoundException
     */
    public Set<GrupoDeUsuarios> getGroupsFromUser(int userId) throws UserNotFoundException {
        Usuario usuario = entityManager.find(Usuario.class, userId);

        if (null == usuario) throw new UserNotFoundException();

        return usuario.getGrupoDeUsuarios();
    }

}
