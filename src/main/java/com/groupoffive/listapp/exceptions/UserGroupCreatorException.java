package com.groupoffive.listapp.exceptions;

public class UserGroupCreatorException extends Exception {

    public UserGroupCreatorException() {
        super("O usuario é o criador do grupo.");
    }
}
