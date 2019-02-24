package com.groupoffive.listapp.exceptions;

public class CategoryNameAlreadyInUseException extends Exception {

    public CategoryNameAlreadyInUseException() {
        super("O nome de categoria informado já está em uso.");
    }

}
