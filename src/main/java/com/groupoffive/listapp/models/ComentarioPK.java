package com.groupoffive.listapp.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ComentarioPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="lista_compra_id", insertable = false, updatable = false)
    private ListaDeCompras list;

    @ManyToOne
    @JoinColumn(name="usuario_id", insertable = false, updatable = false)
    private Usuario user;

    public ComentarioPK(Usuario user, ListaDeCompras list) {
        this.list = list;
        this.user = user;
    }

    public ComentarioPK(ListaDeCompras list, Usuario user){
        this(user, list);
    }

    public ComentarioPK() {
    }

    public ListaDeCompras getList() {
        return list;
    }

    public void setList(ListaDeCompras list) {
        this.list = list;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComentarioPK that = (ComentarioPK) o;
        return Objects.equals(list, that.list) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list, user);
    }
}