package com.groupoffive.listapp.exceptions;

public class ProductAlreadyInListException extends Exception {

    public ProductAlreadyInListException() {
        super("Este produto já está na lista.");
    }
}
