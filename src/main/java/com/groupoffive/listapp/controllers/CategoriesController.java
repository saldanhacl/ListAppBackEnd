package com.groupoffive.listapp.controllers;

import com.groupoffive.listapp.exceptions.CategoryNameAlreadyInUseException;
import com.groupoffive.listapp.exceptions.CategoryNotFoundException;
import com.groupoffive.listapp.models.Categoria;
import com.groupoffive.listapp.models.Produto;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Set;

public class CategoriesController {

    private EntityManager entityManager;

    public CategoriesController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Traz os produtos pertencentes a uma categoria.
     * @param categoryId
     * @return
     * @throws CategoryNotFoundException
     */
    public Set<Produto> getProducts(int categoryId) throws CategoryNotFoundException {
        Categoria categoria = entityManager.find(Categoria.class, categoryId);

        if (null == categoria) throw new CategoryNotFoundException();

        return categoria.getProdutos();
    }

    /**
     * Adiciona uma categoria com o nome informado.
     * @param nome
     * @return
     * @throws CategoryNameAlreadyInUseException
     */
    public Categoria addCategory(String nome) throws CategoryNameAlreadyInUseException {
        if (this.categoryNameIsInUse(nome)) throw new CategoryNameAlreadyInUseException();

        Categoria categoria = new Categoria(nome);

        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        return categoria;
    }

    /**
     * Adiciona uma categoria com o nome informado.
     * Somente realizará commit, caso a variável canCommit seja true.
     * @param nome
     * @return
     * @throws CategoryNameAlreadyInUseException
     */
    Categoria addCategory(String nome, boolean canCommit) throws CategoryNameAlreadyInUseException {
        if (this.categoryNameIsInUse(nome)) throw new CategoryNameAlreadyInUseException();

        Categoria categoria = new Categoria(nome);

        if (!entityManager.getTransaction().isActive()) entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        if (canCommit) entityManager.getTransaction().commit();

        return categoria;
    }

    /**
     * Verifica se o nome informado para a categoria já está em uso.
     * @param nome
     * @return
     */
    private boolean categoryNameIsInUse(String nome) {
        try {
            Categoria categoria = entityManager.createQuery(
                    "SELECT c from Categoria c WHERE c.nome = :nome", Categoria.class
            ).setParameter("nome", nome).getSingleResult();

            return null != categoria;
        } catch (NoResultException e) {
            return false;
        }
    }

}
