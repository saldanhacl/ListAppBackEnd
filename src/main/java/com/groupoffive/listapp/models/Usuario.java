package com.groupoffive.listapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String email;
    private String senha;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "usuario_grupo",
            joinColumns = { @JoinColumn(name = "usuario_id") },
            inverseJoinColumns = { @JoinColumn(name = "grupo_de_usuarios_id") }
    )
    private Set<GrupoDeUsuarios> gruposDeUsuarios = new HashSet<>();

    public Usuario() {}

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public Set<GrupoDeUsuarios> getGrupoDeUsuarios() {
        return gruposDeUsuarios;
    }

    @JsonProperty
    public void setGruposDeUsuarios(Set<GrupoDeUsuarios> gruposDeUsuarios) {
        this.gruposDeUsuarios = gruposDeUsuarios;
    }

}