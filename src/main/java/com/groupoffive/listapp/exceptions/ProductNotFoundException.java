package com.groupoffive.listapp.exceptions;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException() {
        super("Não foi encontrado nenhum produto correspondente.");
    }

}
