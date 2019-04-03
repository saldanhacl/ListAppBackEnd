package com.groupoffive.listapp.routers;

import com.groupoffive.listapp.controllers.CategoriesController;
import com.groupoffive.listapp.exceptions.CategoryNameAlreadyInUseException;
import com.groupoffive.listapp.exceptions.CategoryNotFoundException;
import com.groupoffive.listapp.exceptions.NotFilledRequiredFieldsException;
import com.groupoffive.listapp.models.Categoria;
import com.groupoffive.listapp.models.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "", allowedHeaders = "")
@RequestMapping("/categories")
public class CategoriesRouter {

    private CategoriesController categoriesController;

    public CategoriesRouter(CategoriesController categoriesController) {
        this.categoriesController = categoriesController;
    }

    /**
     * Obtem a lista de categorias existentes
     * Método: GET
     * /categories/
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Set<Categoria> getCategories() {
        return categoriesController.getCategories();
    }

    /**
     * Obtem as categorias recomendadas para a string de nome de produto digitada até o momento
     * Método: GET
     * /categories/recommended/
     * @param nomeProduto
     * @return
     */
    @RequestMapping(value = "/recommended/", method = RequestMethod.GET)
    @ResponseBody
    public Set<Categoria> getRecommendedCategories(String nomeProduto) {
        return categoriesController.getRecommendedCategories(nomeProduto);
    }

    /**
     * Obtem os dados de uma categoria especificada
     * Método: GET
     * /categories/{id}
     * @param categoryId
     * @return
     * @throws CategoryNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Set<Produto> getProductsFromCategory(@PathVariable("id") int categoryId) throws CategoryNotFoundException {
        return categoriesController.getProducts(categoryId);
    }

    /**
     * Adiciona uma nova categoria
     * Método: POST
     * /categories/
     * @param nome
     * @return
     * @throws CategoryNameAlreadyInUseException
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Categoria addCategory(String nome) throws CategoryNameAlreadyInUseException, NotFilledRequiredFieldsException {
        return categoriesController.addCategory(nome);
    }

    /**
     * Remove uma categoria
     * Método: DELETE
     * /categories/{id}
     * @param idCategoria
     * @throws CategoryNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void removeCategory(@PathVariable("id") int idCategoria) throws CategoryNotFoundException {
        categoriesController.removeCategory(idCategoria);
    }

    /**
     * Atualiza uma categoria
     * Método: PUT
     * /categories/{id}
     * @param idCategoria
     * @param nome
     * @throws CategoryNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateCategory(@PathVariable("id") int idCategoria, String nome) throws CategoryNotFoundException {
        categoriesController.updateCategory(idCategoria, nome);
    }

}
