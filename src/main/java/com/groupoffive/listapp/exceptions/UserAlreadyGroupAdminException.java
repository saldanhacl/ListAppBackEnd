package com.groupoffive.listapp.exceptions;

public class UserAlreadyGroupAdminException extends Exception{

    public UserAlreadyGroupAdminException() {
        super("O usuário já é admin do grupo.");
    }
}
