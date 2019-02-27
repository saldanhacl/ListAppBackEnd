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

import java.util.Set;

@RequestMapping("/groups")
public class GroupsRouter {

    private GroupsController groupsController;

    public GroupsRouter(GroupsController groupsController) {
        this.groupsController = groupsController;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Set<GrupoDeUsuarios> getGroupsFromUser(@PathVariable("id") int userId) throws UserNotFoundException {
        return groupsController.getGroupsFromUser(userId);
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

    @RequestMapping(value = "/", method = RequestMethod.PUT, params = {"userId", "groupId"})
    @ResponseBody
    public GrupoDeUsuarios addUserToGroup(int userId, int groupId) throws UserNotFoundException, GroupNotFoundException, UserAlreadyInGroupException {
        return groupsController.addUserToGroup(userId, groupId);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, params = {"userName", "groupId"})
    @ResponseBody
    public GrupoDeUsuarios addUserToGroup(String userName, int groupId) throws UserNotFoundException, GroupNotFoundException, UserAlreadyInGroupException {
        return groupsController.addUserToGroup(userName, groupId);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE, params = {"userId", "groupId"})
    @ResponseBody
    public GrupoDeUsuarios removeUserFromGroup(int userId, int groupId) throws UserNotFoundException, GroupNotFoundException, UserNotInGroupException {
        return groupsController.removeUserFromGroup(userId, groupId);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE, params = {"userName", "groupId"})
    @ResponseBody
    public GrupoDeUsuarios removeUserFromGroup(String userName, int groupId) throws UserNotFoundException, GroupNotFoundException, UserNotInGroupException {
        return groupsController.removeUserFromGroup(userName, groupId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public GrupoDeUsuarios deleteGroup(@PathVariable("id") int groupId) throws UserNotFoundException, GroupNotFoundException, UserAlreadyInGroupException {
        return groupsController.deleteGroup(groupId);
    }



}
