package com.groupoffive.listapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "comentario")
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private ComentarioPK id = new ComentarioPK();

    @Column
    private String comentario;

    public Comentario(Usuario user, ListaDeCompras list, String comentario) {
        this.id = new ComentarioPK(user, list);
        this.comentario = comentario;
    }

    public Comentario(ListaDeCompras list, Usuario user, String comentario) {
        this(user, list, comentario);
    }

    public Comentario() {
    }

    public Usuario getUsuario(){
        return this.id.getUser();
    }

    public void setUsuario(Usuario usuario){
        this.id.setUser(usuario);
    }

    @JsonIgnore
    public ListaDeCompras getGrupo() {
        return this.id.getList();
    }

    @JsonProperty
    public void setGrupo(ListaDeCompras list){
        this.id.setList(list);
    }

    public ComentarioPK getId() {
        return id;
    }

    public void setId(ComentarioPK id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getComentario() {
        return comentario;
    }
}
