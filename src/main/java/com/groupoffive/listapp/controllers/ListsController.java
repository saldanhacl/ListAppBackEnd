package com.groupoffive.listapp.controllers;

import com.groupoffive.listapp.exceptions.GroupNotFoundException;
import com.groupoffive.listapp.exceptions.ListNotFoundException;
import com.groupoffive.listapp.models.Categoria;
import com.groupoffive.listapp.models.GrupoDeUsuarios;
import com.groupoffive.listapp.models.ListaDeCompras;
import com.groupoffive.listapp.models.Produto;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

public class ListsController {

    private EntityManager entityManager;

    public ListsController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void createList(String listName, int groupId) throws GroupNotFoundException {
        GrupoDeUsuarios grupo = entityManager.find(GrupoDeUsuarios.class, groupId);

        if (null == grupo) throw new GroupNotFoundException();
        ListaDeCompras lista = new ListaDeCompras(listName, grupo);
        grupo.getListasDeCompras().add(lista);

        entityManager.getTransaction().begin();
        entityManager.persist(lista);
        entityManager.getTransaction().commit();
    }

    /**
     * Retorna os produtos pertencentes a uma lista.
     * @param listId id da lista a ser buscada
     * @return devolve os produtos da lista
     * @throws ListNotFoundException Exceção lançada caso lista com este id não seja encontrada
     */
    public Set<Produto> getListProducts(int listId) throws ListNotFoundException {
        ListaDeCompras lista = entityManager.find(ListaDeCompras.class, listId);

        if (null == lista) throw new ListNotFoundException();

        return lista.getProdutos();
    }

    /**
     * Retorna as categories pertencentes a uma lista.
     * @param listId id da lista a ser buscada
     * @return devolve as categorias da lista
     * @throws ListNotFoundException Exceção lançada caso lista com este id não seja encontrada
     */
    public Set<Categoria> getListCategories(int listId) throws ListNotFoundException {
        Set<Produto> produtos;
        Set<Categoria> categorias = new HashSet<>();
        ListaDeCompras lista  = entityManager.find(ListaDeCompras.class, listId);

        if (null == lista) throw new ListNotFoundException();

        produtos = lista.getProdutos();

        for (Produto produto : produtos) {
            categorias.add(produto.getCategoria());
        }

        return categorias;
    }

    /**
     * Remove todos os produtos de uma lista e logo em seguida remove a lista
     * @param listId id da lista a ser removida
     * @throws ListNotFoundException Exceção lançada caso lista com este id não seja encontrada
     */
    public void deleteList(int listId) throws ListNotFoundException {
        ListaDeCompras lista = entityManager.find(ListaDeCompras.class, listId);

        if (null == lista) throw new ListNotFoundException();

        entityManager.getTransaction().begin();
        for (Produto produto : lista.getProdutos()) {
            entityManager.remove(produto);
        }
        entityManager.remove(lista);
        entityManager.getTransaction().commit();
    }

    /**
     * Altera as informações de uma lista
     * @param listId id da lista a ser atualizada
     * @param nomeLista nome a ser atribuído para a lista
     * @throws ListNotFoundException Exceção lançada caso lista com este id não seja encontrada
     */
    public void updateList(int listId, String nomeLista) throws ListNotFoundException {
        ListaDeCompras lista = entityManager.find(ListaDeCompras.class, listId);

        if (null == lista) throw new ListNotFoundException();

        entityManager.getTransaction().begin();
        lista.setNome(nomeLista);
        entityManager.getTransaction().commit();
    }

}
