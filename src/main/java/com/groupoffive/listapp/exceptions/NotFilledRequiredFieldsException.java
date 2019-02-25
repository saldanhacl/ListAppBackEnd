package com.groupoffive.listapp.exceptions;

public class NotFilledRequiredFieldsException extends Exception {

    public NotFilledRequiredFieldsException() {
        super("Todos os campos obrigatorios precisam ser preenchidos.");
    }
}
