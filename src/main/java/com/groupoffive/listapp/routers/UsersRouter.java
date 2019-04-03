package com.groupoffive.listapp.routers;

import com.groupoffive.listapp.controllers.UsersController;
import com.groupoffive.listapp.exceptions.EmailAlreadyInUseException;
import com.groupoffive.listapp.exceptions.IncorrectEmailOrPasswordException;
import com.groupoffive.listapp.exceptions.NotFilledRequiredFieldsException;
import com.groupoffive.listapp.exceptions.UserNotFoundException;
import com.groupoffive.listapp.models.GrupoDeUsuarios;
import com.groupoffive.listapp.models.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "", allowedHeaders = "")
@RequestMapping("/users")
public class UsersRouter {

    private UsersController usersController;

    public UsersRouter(UsersController usersController) {
        this.usersController = usersController;
    }

    /**
     * Realiza a autenticação de um usuário
     * Método: POST
     * /users/login
     * @param email
     * @param senha
     * @return
     * @throws IncorrectEmailOrPasswordException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Usuario login(String email, String senha) throws IncorrectEmailOrPasswordException {
        return usersController.login(email, senha);
    }

    /**
     * Cadastra um novo usuário
     * Método: POST
     * /users/
     * @param nome
     * @param email
     * @param senha
     * @return
     * @throws NotFilledRequiredFieldsException
     * @throws EmailAlreadyInUseException
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Usuario addUser(String nome, String email, String senha) throws NotFilledRequiredFieldsException, EmailAlreadyInUseException {
        return usersController.addUser(nome, email, senha);
    }

    /**
     * Obtem os grupos ao qual este usuário pertence
     * Método: GET
     * /users/{id}/groups
     * @param groupId
     * @return
     * @throws UserNotFoundException
     */
    @RequestMapping(value = "/{id}/groups", method = RequestMethod.GET)
    @ResponseBody
    public Set<GrupoDeUsuarios> getGroupsFromUser(@PathVariable("id") int groupId) throws UserNotFoundException {
        return usersController.getGroupsFromUser(groupId);
    }

}
