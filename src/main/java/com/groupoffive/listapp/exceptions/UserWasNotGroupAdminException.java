package com.groupoffive.listapp.exceptions;

public class UserWasNotGroupAdminException extends Exception {

    public UserWasNotGroupAdminException() {
        super("O usuário não é admin do grupo.");
    }
}
