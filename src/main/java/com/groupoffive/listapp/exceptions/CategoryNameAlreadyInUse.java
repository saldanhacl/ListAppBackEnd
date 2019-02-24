package com.groupoffive.listapp.exceptions;

public class CategoryNameAlreadyInUse extends Exception {

    public CategoryNameAlreadyInUse() {
        super("O nome de categoria informado já está em uso.");
    }

}
