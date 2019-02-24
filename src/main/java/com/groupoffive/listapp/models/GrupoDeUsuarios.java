package com.groupoffive.listapp.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "grupo_de_usuarios")
public class GrupoDeUsuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "usuario_grupo",
            joinColumns = { @JoinColumn(name = "grupo_de_usuarios_id") },
            inverseJoinColumns = { @JoinColumn(name = "usuario_id") }
    )
    private Set<Usuario> usuarios = new HashSet<>();
    @OneToMany(mappedBy="grupoDeUsuarios")
    private Set<ListaDeCompras> listasDeCompras = new HashSet<>();

    public GrupoDeUsuarios() {}

    public GrupoDeUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public int getId() {
        return id;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<ListaDeCompras> getListasDeCompras() {
        return listasDeCompras;
    }

    public void setListasDeCompras(Set<ListaDeCompras> listasDeCompras) {
        this.listasDeCompras = listasDeCompras;
    }

}