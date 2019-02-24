package com.groupoffive.listapp.exceptions;

public class ProductNameAlreadyInUseException extends Exception {

    public ProductNameAlreadyInUseException() {
        super("O nome de produto informado já está em uso.");
    }

}
