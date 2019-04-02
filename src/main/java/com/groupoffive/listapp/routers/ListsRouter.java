package com.groupoffive.listapp.routers;

import com.groupoffive.listapp.controllers.ListsController;
import com.groupoffive.listapp.exceptions.ListNotFoundException;
import com.groupoffive.listapp.models.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RequestMapping("/lists")
public class ListsRouter {

    private ListsController listsController;

    public ListsRouter(ListsController listsController) {
        this.listsController = listsController;
    }

    /**
     * Obtem os dados de uma lista
     * Método: GET
     * /lists/{id}
     * @param listId
     * @return
     * @throws ListNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Set<Produto> getListProducts(@PathVariable("id") int listId) throws ListNotFoundException {
        return listsController.getListProducts(listId);
    }

    /**
     * Remove uma lista
     * Método: DELETE
     * /lists/{id}
     * @param listId
     * @throws ListNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteList(@PathVariable("id") int listId) throws ListNotFoundException {
        listsController.deleteList(listId);
    }

    /**
     * Atualiza uma lista
     * Método: PUT
     * /lists/{id}
     * @param listId
     * @param listName
     * @throws ListNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateList(@PathVariable("id") int listId, String listName) throws ListNotFoundException {
        listsController.updateList(listId, listName);
    }

}
