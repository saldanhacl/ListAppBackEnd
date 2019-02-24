package com.groupoffive.listapp.exceptions;

public class CategoryNotFoundException extends Exception {

    public CategoryNotFoundException() {
        super("Não foi encontrada nenhuma categoria correspondente!");
    }

}
