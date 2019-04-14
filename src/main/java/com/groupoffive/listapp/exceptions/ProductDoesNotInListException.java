package com.groupoffive.listapp.exceptions;

public class ProductDoesNotInListException extends Exception {

    public ProductDoesNotInListException() {
        super("Este produto nao esta na lista.");
    }
}
