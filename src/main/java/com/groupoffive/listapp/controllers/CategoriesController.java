package com.groupoffive.listapp.controllers;

import com.groupoffive.listapp.exceptions.CategoryNameAlreadyInUse;
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

    public Categoria addCategory(String nome) throws CategoryNameAlreadyInUse {
        if (this.categoryNameIsInUse(nome)) throw new CategoryNameAlreadyInUse();

        Categoria categoria = new Categoria(nome);

        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        return categoria;
    }

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
