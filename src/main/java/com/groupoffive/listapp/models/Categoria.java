package com.groupoffive.listapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    @OneToMany(mappedBy="categoria")
    private Set<Produto> produtos = new HashSet<>();

    public Categoria() {}

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(String nome, Set<Produto> produtos) {
        this.nome     = nome;
        this.produtos = produtos;
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

    @JsonIgnore
    public Set<Produto> getProdutos() {
        return produtos;
    }

    @JsonProperty
    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }
}