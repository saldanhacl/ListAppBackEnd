package com.groupoffive.listapp.controllers;

import com.groupoffive.listapp.exceptions.CategoryNotFoundException;
import com.groupoffive.listapp.models.Categoria;
import com.groupoffive.listapp.models.Produto;

import javax.persistence.EntityManager;
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

}
