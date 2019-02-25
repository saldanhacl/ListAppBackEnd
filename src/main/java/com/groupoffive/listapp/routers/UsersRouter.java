package com.groupoffive.listapp.routers;

import com.groupoffive.listapp.controllers.UsersController;
import com.groupoffive.listapp.exceptions.EmailAlreadyInUseException;
import com.groupoffive.listapp.exceptions.IncorrectEmailOrPasswordException;
import com.groupoffive.listapp.exceptions.NotFilledRequiredFieldsException;
import com.groupoffive.listapp.models.Usuario;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/users")
public class UsersRouter {

    private UsersController usersController;

    public UsersRouter(UsersController usersController) {
        this.usersController = usersController;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Usuario login(String email, String senha) throws IncorrectEmailOrPasswordException {
        return usersController.login(email, senha);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Usuario addUser(String nome, String email, String senha) throws NotFilledRequiredFieldsException, EmailAlreadyInUseException {
        return usersController.addUser(nome, email, senha);
    }

}
