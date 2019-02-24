package com.groupoffive.listapp.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("Não foi encontrado nenhum usuário correspondente.");
    }

}
