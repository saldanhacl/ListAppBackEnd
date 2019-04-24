package com.groupoffive.listapp.exceptions;

public class EmptyCommentException extends Exception {

    public EmptyCommentException() {
        super("Insira um comentário válido.");
    }
}
