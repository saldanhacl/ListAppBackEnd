package com.groupoffive.listapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private double preco;
    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria;

    public Produto() {}

    public Produto(String nome, double preco) {
        this.nome  = nome;
        this.preco = preco;
    }

    public Produto(String nome, double preco, Categoria categoria) {
        this.nome      = nome;
        this.preco     = preco;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @JsonIgnore
    public Categoria getCategoria() {
        return categoria;
    }

    @JsonIgnore
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}