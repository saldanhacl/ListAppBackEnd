package com.groupoffive.listapp.exceptions;

public class UserNotInGroupException extends Exception {

    public UserNotInGroupException() {
        super("O usuario nao percence a este grupo.");
    }

}
