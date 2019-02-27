package com.groupoffive.listapp.exceptions;

public class UserAlreadyInGroupException extends Exception{

    public UserAlreadyInGroupException() {
        super("O usuario ja esta neste grupo.");
    }
}
