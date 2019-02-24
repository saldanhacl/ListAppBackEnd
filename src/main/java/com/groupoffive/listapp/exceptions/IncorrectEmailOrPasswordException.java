package com.groupoffive.listapp.exceptions;

public class IncorrectEmailOrPasswordException extends Exception {

    public IncorrectEmailOrPasswordException() {
        super("O e-mail e/ou senha informados estão incorretos.");
    }

}
