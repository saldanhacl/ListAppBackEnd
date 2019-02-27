package com.groupoffive.listapp.controllers;

import com.groupoffive.listapp.exceptions.GroupNotFoundException;
import com.groupoffive.listapp.exceptions.UserAlreadyInGroupException;
import com.groupoffive.listapp.exceptions.UserNotFoundException;
import com.groupoffive.listapp.exceptions.UserNotInGroupException;
import com.groupoffive.listapp.models.GrupoDeUsuarios;
import com.groupoffive.listapp.models.Usuario;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.HashSet;
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
     * @throws UserNotFoundException caso o usuario solicitado nao esteja cadastrado ou nao esteja em algum grupo
     */
    public Set<GrupoDeUsuarios> getGroupsFromUser(int userId) throws UserNotFoundException {
        Usuario usuario = entityManager.find(Usuario.class, userId);

        if (null == usuario) throw new UserNotFoundException();

        return usuario.getGrupoDeUsuarios();
    }

    /**
     *
     * CREATE GROUP
     */
    private GrupoDeUsuarios createGroup(String nome, Usuario criador) throws UserNotFoundException{
        if(criador == null) throw new UserNotFoundException();

        GrupoDeUsuarios group = new GrupoDeUsuarios(nome, criador);

        entityManager.getTransaction().begin();
        entityManager.persist(group);
        entityManager.getTransaction().commit();

        group.getCriador().getGrupoDeUsuarios().add(group);

        return group;
    }
    public GrupoDeUsuarios createGroup(String nome, int creator_id) throws UserNotFoundException{
        Usuario criador = entityManager.find(Usuario.class, creator_id);

        if(criador == null) throw new UserNotFoundException();

        return createGroup(nome, criador);
    }
    public GrupoDeUsuarios createGroup(String nome, String creator_name) throws UserNotFoundException{
        Usuario criador = getUserByName(creator_name);

        if(criador == null) throw new UserNotFoundException();

        return createGroup(nome, criador);
    }

    /**
     *
     * ADD USER
     */
    private GrupoDeUsuarios addUserToGroup(Usuario user, GrupoDeUsuarios group) throws UserNotFoundException, GroupNotFoundException, UserAlreadyInGroupException{
        if(user == null) throw new UserNotFoundException();
        if(group == null) throw new GroupNotFoundException();

        if(user.getGrupoDeUsuarios().contains(group)) throw new UserAlreadyInGroupException();

        group.getUsuarios().add(user);

        entityManager.getTransaction().begin();
        entityManager.persist(group);
        entityManager.getTransaction().commit();

        user.getGrupoDeUsuarios().add(group);

        return group;
    }
    public GrupoDeUsuarios addUserToGroup(int user_id, int group_id) throws UserNotFoundException, GroupNotFoundException, UserAlreadyInGroupException{
        Usuario user = entityManager.find(Usuario.class, user_id);
        GrupoDeUsuarios group = entityManager.find(GrupoDeUsuarios.class, group_id);

        return addUserToGroup(user, group);
    }
    public GrupoDeUsuarios addUserToGroup(String user_name, int group_id) throws UserNotFoundException, GroupNotFoundException, UserAlreadyInGroupException{
        Usuario user = getUserByName(user_name);
        GrupoDeUsuarios group = entityManager.find(GrupoDeUsuarios.class, group_id);

        return addUserToGroup(user, group);
    }

    /**
     * -- Melhorar logica:
     * -- - Deletar grupo caso  fique 0 (zero) usuarios
     * -- - Transferir admin caso o criador saia
     *
     * REMOVE USER
     */
    private GrupoDeUsuarios removeUserFromGroup(Usuario user, GrupoDeUsuarios group) throws UserNotFoundException, GroupNotFoundException, UserNotInGroupException{
        if(user == null) throw new UserNotFoundException();
        if(group == null) throw new GroupNotFoundException();

        if(!group.getUsuarios().contains(user)) throw new UserNotInGroupException();

        group.getUsuarios().remove(user);

        entityManager.getTransaction().begin();
        entityManager.persist(group);
        entityManager.getTransaction().commit();

        user.getGrupoDeUsuarios().remove(group);

        return group;
    }
    public GrupoDeUsuarios removeUserFromGroup(int user_id, int group_id) throws UserNotFoundException, GroupNotFoundException, UserNotInGroupException{
        Usuario user = entityManager.find(Usuario.class, user_id);
        GrupoDeUsuarios group = entityManager.find(GrupoDeUsuarios.class, group_id);

        return removeUserFromGroup(user, group);
    }
    public GrupoDeUsuarios removeUserFromGroup(String user_name, int group_id) throws UserNotFoundException, GroupNotFoundException, UserNotInGroupException{
        Usuario user = getUserByName(user_name);
        GrupoDeUsuarios group = entityManager.find(GrupoDeUsuarios.class, group_id);

        return removeUserFromGroup(user, group);
    }

    public void makeUserGroupAdmin(){

    }

    public void makeUserGroupOwner(){

    }

    /**
     *
     *  DELETE GROUP
     */
    private GrupoDeUsuarios deleteGroup(GrupoDeUsuarios group) throws GroupNotFoundException{
        try {
            if (group == null) throw new GroupNotFoundException();

            group.getUsuarios().forEach(user -> user.getGrupoDeUsuarios().remove(group));

            group.setUsuarios(new HashSet<>());

            entityManager.getTransaction().begin();
            entityManager.remove(group);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            return null;
        }

        return group;
    }
    public GrupoDeUsuarios deleteGroup(int groupId) throws GroupNotFoundException{
        GrupoDeUsuarios group = entityManager.find(GrupoDeUsuarios.class, groupId);

        return deleteGroup(group);
    }

    private Usuario getUserByName(String nome){
        try{

            return entityManager.createQuery("SELECT u from Usuario u WHERE u.nome = :nome", Usuario.class).setParameter("nome", nome).getSingleResult();

        }catch(NoResultException e){
            return null;
        }
    }


}
