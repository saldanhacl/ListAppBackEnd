package com.groupoffive.listapp.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "grupo_de_usuarios")
public class GrupoDeUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", updatable = false, nullable = false)
    private int id;

    private String nome;

    @OneToMany(mappedBy = "id.group")
    private Set<UsuarioGrupo> usuarios = new HashSet<>();

    @OneToMany(mappedBy="grupoDeUsuarios")
    private Set<ListaDeCompras> listasDeCompras = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="criador")
    private Usuario criador;

    public GrupoDeUsuarios() {}

    public GrupoDeUsuarios(String nome, Usuario criador) {
        this.nome = nome;
        this.criador = criador;
    }

    private GrupoDeUsuarios(String nome, Usuario criador, Set<UsuarioGrupo> usuarios) {
        this.nome = nome;
        this.criador = criador;
        this.usuarios = usuarios;
    }

    public int getId() {
        return id;
    }

    public Set<UsuarioGrupo> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<UsuarioGrupo> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<ListaDeCompras> getListasDeCompras() {
        return listasDeCompras;
    }

    public void setListasDeCompras(Set<ListaDeCompras> listasDeCompras) {
        this.listasDeCompras = listasDeCompras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        GrupoDeUsuarios group = new GrupoDeUsuarios(this.nome, this.criador, this.getUsuarios());
        group.setListasDeCompras(this.getListasDeCompras());

        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoDeUsuarios that = (GrupoDeUsuarios) o;
        return id == that.id &&
                nome.equals(that.nome) &&
                criador.equals(that.criador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, criador);
    }
}