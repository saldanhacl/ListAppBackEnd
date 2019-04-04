package com.groupoffive.listapp.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produto_lista")
public class ProdutoLista {

    @ManyToOne
    @JoinColumn(name="lista_de_compras_id")
    private ListaDeCompras listaDeCompras;
    @ManyToOne
    @JoinColumn(name="produto_id")
    private Produto produto;

    public ProdutoLista(ListaDeCompras listaDeCompras, Produto produto) {
        this.listaDeCompras = listaDeCompras;
        this.produto = produto;
    }

}