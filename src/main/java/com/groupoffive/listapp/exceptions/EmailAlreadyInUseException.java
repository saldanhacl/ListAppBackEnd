package com.groupoffive.listapp.exceptions;

public class EmailAlreadyInUseException extends Exception {

    public EmailAlreadyInUseException() {
        super("Este email já está em uso.");
    }

}
