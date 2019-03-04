package com.groupoffive.listapp.routers;

import com.groupoffive.listapp.controllers.GroupsController;
import com.groupoffive.listapp.exceptions.GroupNotFoundException;
import com.groupoffive.listapp.exceptions.UserAlreadyInGroupException;
import com.groupoffive.listapp.exceptions.UserNotFoundException;
import com.groupoffive.listapp.exceptions.UserNotInGroupException;
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

    @RequestMapping(value = "/", method = RequestMethod.POST, params = {"nome", "idCriador"})
    @ResponseBody
    public GrupoDeUsuarios createGroup(String nome, int idCriador) throws UserNotFoundException{
        return groupsController.createGroup(nome, idCriador);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = {"nome", "nomeCriador"})
    @ResponseBody
    public GrupoDeUsuarios createGroup(String nome, String nomeCriador) throws UserNotFoundException{
        return groupsController.createGroup(nome, nomeCriador);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GrupoDeUsuarios getGroup(@PathVariable("id")int groupId) throws GroupNotFoundException{
        return groupsController.getGroup(groupId);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.PUT, params = {"userId"})
    @ResponseBody
    public GrupoDeUsuarios addUserToGroup(@PathVariable("id")int groupId, int userId) throws UserNotFoundException, GroupNotFoundException, UserAlreadyInGroupException {
        return groupsController.addUserToGroup(userId, groupId);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.PUT, params = {"userName"})
    @ResponseBody
    public GrupoDeUsuarios addUserToGroup(@PathVariable("id")int groupId, String userName) throws UserNotFoundException, GroupNotFoundException, UserAlreadyInGroupException {
        return groupsController.addUserToGroup(userName, groupId);
    }

    @RequestMapping(value = "/{id}/user/", method = RequestMethod.DELETE, params = {"userId"})
    @ResponseBody
    public GrupoDeUsuarios removeUserFromGroup(@PathVariable("id")int groupId, int userId) throws UserNotFoundException, GroupNotFoundException, UserNotInGroupException {
        return groupsController.removeUserFromGroup(userId, groupId);
    }

    @RequestMapping(value = "/{id}/user/", method = RequestMethod.DELETE, params = {"userName"})
    @ResponseBody
    public GrupoDeUsuarios removeUserFromGroup(@PathVariable("id")int groupId, String userName) throws UserNotFoundException, GroupNotFoundException, UserNotInGroupException {
        return groupsController.removeUserFromGroup(userName, groupId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public GrupoDeUsuarios deleteGroup(@PathVariable("id") int groupId) throws GroupNotFoundException {
        return groupsController.deleteGroup(groupId);
    }

}
