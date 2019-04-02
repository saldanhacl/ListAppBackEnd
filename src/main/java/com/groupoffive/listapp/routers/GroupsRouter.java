package com.groupoffive.listapp.routers;

import com.groupoffive.listapp.controllers.GroupsController;
import com.groupoffive.listapp.exceptions.*;
import com.groupoffive.listapp.models.GrupoDeUsuarios;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/groups")
public class GroupsRouter {

    private GroupsController groupsController;

    public GroupsRouter(GroupsController groupsController) {
        this.groupsController = groupsController;
    }

    /**
     * Cria um grupo
     * Método: POST
     * /groups/
     * @param nome
     * @param idCriador
     * @return
     * @throws UserNotFoundException
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, params = {"nome", "idCriador"})
    @ResponseBody
    public GrupoDeUsuarios createGroup(String nome, int idCriador) throws UserNotFoundException{
        return groupsController.createGroup(nome, idCriador);
    }

    /**
     * Cria um grupo
     * Método: POST
     * /groups/
     * @param nome
     * @param nomeCriador
     * @return
     * @throws UserNotFoundException
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, params = {"nome", "nomeCriador"})
    @ResponseBody
    public GrupoDeUsuarios createGroup(String nome, String nomeCriador) throws UserNotFoundException{
        return groupsController.createGroup(nome, nomeCriador);
    }

    /**
     * Obtem os dados de um grupo
     * Método: GET
     * /groups/{id}
     * @param groupId
     * @return
     * @throws GroupNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GrupoDeUsuarios getGroup(@PathVariable("id")int groupId) throws GroupNotFoundException{
        return groupsController.getGroup(groupId);
    }

    /**
     * Relaciona um usuário a um grupo
     * Método: PUT
     * /groups/{id}
     * @param groupId
     * @param userId
     * @return
     * @throws GroupNotFoundException
     * @throws UserNotFoundException
     * @throws UserAlreadyInGroupException
     */
    @RequestMapping(value = "/{id}/", method = RequestMethod.PUT, params = {"userId"})
    @ResponseBody
    public GrupoDeUsuarios addUserToGroup(@PathVariable("id")int groupId, int userId) throws GroupNotFoundException, UserNotFoundException, UserAlreadyInGroupException {
        return groupsController.addUserToGroup(userId, groupId);
    }

    /**
     * Relaciona um usuário a um grupo
     * Método: PUT
     * /groups/{id}
     * @param groupId
     * @param userName
     * @return
     * @throws GroupNotFoundException
     * @throws UserNotFoundException
     * @throws UserAlreadyInGroupException
     */
    @RequestMapping(value = "/{id}/", method = RequestMethod.PUT, params = {"userName"})
    @ResponseBody
    public GrupoDeUsuarios addUserToGroup(@PathVariable("id")int groupId, String userName) throws GroupNotFoundException, UserNotFoundException, UserAlreadyInGroupException {
        return groupsController.addUserToGroup(userName, groupId);
    }

    /**
     * Alterna o status de um usuário como admin em determinado grupo
     * Método: PUT
     * /groups/{id}/user/
     * @param groupId
     * @param userId
     * @param admin
     * @return
     * @throws UserNotFoundException
     * @throws GroupNotFoundException
     * @throws UserNotInGroupException
     * @throws UserGroupCreatorException
     * @throws UserAlreadyGroupAdminException
     * @throws UserWasNotGroupAdminException
     */
    @RequestMapping(value = "/{id}/user/", method = RequestMethod.PUT, params = {"userId", "admin"})
    @ResponseBody
    public GrupoDeUsuarios toggleUserGroupAdmin(@PathVariable("id")int groupId, int userId, boolean admin)
            throws UserNotFoundException, GroupNotFoundException, UserNotInGroupException, UserGroupCreatorException, UserAlreadyGroupAdminException, UserWasNotGroupAdminException {
        return groupsController.toggleUserGroupAdmin(userId, groupId, admin);
    }

    /**
     * Alterna o status de um usuário como admin em determinado grupo
     * Método: PUT
     * /groups/{id}/user/
     * @param groupId
     * @param userName
     * @param admin
     * @return
     * @throws UserNotFoundException
     * @throws GroupNotFoundException
     * @throws UserNotInGroupException
     * @throws UserGroupCreatorException
     * @throws UserAlreadyGroupAdminException
     * @throws UserWasNotGroupAdminException
     */
    @RequestMapping(value = "/{id}/user/", method = RequestMethod.PUT, params = {"userName", "admin"})
    @ResponseBody
    public GrupoDeUsuarios toggleUserGroupAdmin(@PathVariable("id")int groupId, String userName, boolean admin)
            throws UserNotFoundException, GroupNotFoundException, UserNotInGroupException, UserGroupCreatorException, UserAlreadyGroupAdminException, UserWasNotGroupAdminException {
        return groupsController.toggleUserGroupAdmin(userName, groupId, admin);
    }

    /**
     * Remove um usuário de um grupo
     * Método: DELETE
     * /groups/{id}/user/
     * @param groupId
     * @param userId
     * @return
     * @throws GroupNotFoundException
     * @throws UserNotFoundException
     * @throws UserNotInGroupException
     */
    @RequestMapping(value = "/{id}/user/", method = RequestMethod.DELETE, params = {"userId"})
    @ResponseBody
    public GrupoDeUsuarios removeUserFromGroup(@PathVariable("id")int groupId, int userId) throws GroupNotFoundException, UserNotFoundException, UserNotInGroupException {
        return groupsController.removeUserFromGroup(userId, groupId);
    }

    /**
     * Remove um usuário de um grupo
     * Método: DELETE
     * /groups/{id}/user/
     * @param groupId
     * @param userName
     * @return
     * @throws GroupNotFoundException
     * @throws UserNotFoundException
     * @throws UserNotInGroupException
     */
    @RequestMapping(value = "/{id}/user/", method = RequestMethod.DELETE, params = {"userName"})
    @ResponseBody
    public GrupoDeUsuarios removeUserFromGroup(@PathVariable("id")int groupId, String userName) throws GroupNotFoundException, UserNotFoundException, UserNotInGroupException {
        return groupsController.removeUserFromGroup(userName, groupId);
    }

    /**
     * Deleta um grupo
     * Método: DELETE
     * /groups/{id}
     * @param groupId
     * @return
     * @throws GroupNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public GrupoDeUsuarios deleteGroup(@PathVariable("id") int groupId) throws GroupNotFoundException {
        return groupsController.deleteGroup(groupId);
    }

}
