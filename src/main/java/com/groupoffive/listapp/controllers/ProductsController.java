package com.groupoffive.listapp.controllers;

import com.groupoffive.listapp.AppConfig;
import com.groupoffive.listapp.exceptions.*;
import com.groupoffive.listapp.models.Categoria;
import com.groupoffive.listapp.models.ListaDeCompras;
import com.groupoffive.listapp.models.Produto;
import com.groupoffive.listapp.models.ProdutoLista;
import com.groupoffive.listapp.util.Levenshtein;
import com.groupoffive.listapp.util.MapSorter;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.*;

public class ProductsController {

    private EntityManager entityManager;

    public ProductsController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Set<Produto> getRecommendedProducts(String nomeProduto) {
        List<Produto> lista         = entityManager.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
        Set<Produto> retorno        = new LinkedHashSet<>();
        Map<Produto, Double> map    = new LinkedHashMap<>();

        /* Compara os nomes dos produtos com o do produto digitado */
        for (Produto produto : lista) {
            Double distancia = Levenshtein.stringsDistance(nomeProduto, produto.getNome());
            if (distancia < 0.4d) map.put(produto, distancia);
        }

        /* Ordena o map de produtos pelos mais relevantes */
        Map mapProcessado = MapSorter.sortByValues(map);

        /* Preenchendo lista com os valores do map */
        mapProcessado.forEach((k,v) -> retorno.add((Produto) k));

        return retorno;
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

        if (!this.entityManager.getTransaction().isActive()) this.entityManager.getTransaction().begin();
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
            Categoria categoria = AppConfig.getContext().getBean("categoriesController", CategoriesController.class).addCategory(nomeCategoria, false);
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

    /**
     * Remove o produto informado.
     * @param idProduto
     * @throws ProductNotFoundException
     */
    public void removeProduct(int idProduto) throws ProductNotFoundException {
        Produto produto = entityManager.find(Produto.class, idProduto);

        if (null == produto) throw new ProductNotFoundException();

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();
    }

    /**
     * Altera os dados de um produto. Atribui ele a uma categoria já existente.
     * @param idProduto
     * @param nome
     * @param preco
     * @param idCategoria
     * @throws ProductNotFoundException
     * @throws CategoryNotFoundException
     */
    public void updateProduct(int idProduto, String nome, double preco, int idCategoria)
            throws ProductNotFoundException, CategoryNotFoundException {
        Produto produto     = entityManager.find(Produto.class, idProduto);
        Categoria categoria = entityManager.find(Categoria.class, idCategoria);

        if (null == produto) throw new ProductNotFoundException();
        if (null == categoria) throw new CategoryNotFoundException();

        entityManager.getTransaction().begin();
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setCategoria(categoria);
        entityManager.getTransaction().commit();
    }

    /**
     * Altera os dados de um produto. Atribui ele a uma nova categoria.
     * @param nome
     * @param preco
     * @param nomeCategoria
     * @return
     * @throws ProductNameAlreadyInUseException
     * @throws CategoryNameAlreadyInUseException
     */
    public void updateProduct(int idProduto, String nome, double preco, String nomeCategoria) throws ProductNotFoundException, CategoryNameAlreadyInUseException {
        try {
            Categoria categoria = AppConfig.getContext().getBean("categoriesController", CategoriesController.class).addCategory(nomeCategoria, false);
            this.updateProduct(idProduto, nome, preco, categoria.getId());
        } catch (CategoryNotFoundException e) {
            // Tenho muita fé de que isso não vai acontecer
        }
    }

    public void addProductToList(int idProduto, int idLista) throws ProductNotFoundException, ListNotFoundException {
        Produto produto      = entityManager.find(Produto.class, idProduto);
        if (null == produto) throw new ProductNotFoundException();

        ListaDeCompras lista = entityManager.find(ListaDeCompras.class, idLista);
        if (null == lista) throw new ListNotFoundException();

        ProdutoLista pl      = new ProdutoLista(lista, produto);
        entityManager.getTransaction().begin();
        entityManager.persist(pl);
        entityManager.getTransaction().commit();
    }

}
