package com.groupoffive.listapp.routers;

import com.groupoffive.listapp.controllers.GroupsController;
import com.groupoffive.listapp.exceptions.UserNotFoundException;
import com.groupoffive.listapp.models.GrupoDeUsuarios;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Set<GrupoDeUsuarios> getGroupsFromUser(int userId) throws UserNotFoundException {
        return groupsController.getGroupsFromUser(userId);
    }

}
