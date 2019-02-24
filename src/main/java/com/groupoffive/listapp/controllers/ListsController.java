package com.groupoffive.listapp.controllers;

import com.groupoffive.listapp.exceptions.GroupNotFoundException;
import com.groupoffive.listapp.exceptions.ListNotFoundException;
import com.groupoffive.listapp.models.ListaDeCompras;
import com.groupoffive.listapp.models.Produto;

import javax.persistence.EntityManager;
import java.util.Set;

public class ListsController {

    private EntityManager entityManager;

    public ListsController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Retorna os produtos pertencentes a uma lista.
     * @param listId
     * @return
     * @throws GroupNotFoundException
     */
    public Set<Produto> getListProducts(int listId) throws ListNotFoundException {
        ListaDeCompras lista = entityManager.find(ListaDeCompras.class, listId);

        if (null == lista) throw new ListNotFoundException();

        return lista.getProdutos();
    }

}
