package com.groupoffive.listapp.controllers;

import com.groupoffive.listapp.AppConfig;
import com.groupoffive.listapp.exceptions.CategoryNameAlreadyInUseException;
import com.groupoffive.listapp.exceptions.CategoryNotFoundException;
import com.groupoffive.listapp.exceptions.ProductNameAlreadyInUseException;
import com.groupoffive.listapp.models.Categoria;
import com.groupoffive.listapp.models.Produto;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class ProductsController {

    private EntityManager entityManager;

    public ProductsController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Adiciona um novo produto a uma categoria existente.
     * @param nome
     * @param preco
     * @param idCategoria
     * @return
     */
    public Produto addProduct(String nome, double preco, int idCategoria) throws ProductNameAlreadyInUseException, CategoryNotFoundException {
        if (this.productNameIsInUse(nome)) throw new ProductNameAlreadyInUseException();

        Categoria categoria = this.entityManager.find(Categoria.class, idCategoria);
        if (null == categoria) throw new CategoryNotFoundException();

        Produto produto = new Produto(nome, preco, categoria);

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(produto);
        this.entityManager.getTransaction().commit();

        return produto;
    }

    /**
     * Adiciona um novo produto a uma nova categoria.
     * @param nome
     * @param preco
     * @param nomeCategoria
     * @return
     * @throws ProductNameAlreadyInUseException
     * @throws CategoryNameAlreadyInUseException
     */
    public Produto addProduct(String nome, double preco, String nomeCategoria) throws ProductNameAlreadyInUseException, CategoryNameAlreadyInUseException {
        try {
            Categoria categoria = AppConfig.getContext().getBean("categoriesController", CategoriesController.class).addCategory(nomeCategoria);
            return this.addProduct(nome, preco, categoria.getId());
        } catch (CategoryNotFoundException e) {
            // Tenho muita fé de que isso não vai acontecer
            return null;
        }
    }

    /**
     * Verifica se o nome informado para o produto já está em uso.
     * @param nome
     * @return
     */
    private boolean productNameIsInUse(String nome) {
        try {
            Produto produto = entityManager.createQuery(
                    "SELECT p from Produto p WHERE p.nome = :nome", Produto.class
            ).setParameter("nome", nome).getSingleResult();

            return null != produto;
        } catch (NoResultException e) {
            return false;
        }
    }

}
