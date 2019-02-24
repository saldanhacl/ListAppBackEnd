package com.groupoffive.listapp.exceptions;

public class ListNotFoundException extends Exception {

    public ListNotFoundException() {
        super("Não foi encontrada nenhuma lista correspondente.");
    }

}
