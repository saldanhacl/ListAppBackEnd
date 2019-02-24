package com.groupoffive.listapp.exceptions;

public class GroupNotFoundException extends Exception {

    public GroupNotFoundException() {
        super("Não foi encontrado nenhum grupo correspondente.");
    }

}
