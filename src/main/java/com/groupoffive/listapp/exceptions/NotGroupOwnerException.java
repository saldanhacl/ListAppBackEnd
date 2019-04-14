package com.groupoffive.listapp.exceptions;

public class NotGroupOwnerException extends Exception {

    public NotGroupOwnerException() {
        super("Apenas o criador do grupo pode o deletar.");
    }
}
