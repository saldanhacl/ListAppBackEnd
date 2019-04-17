package com.groupoffive.listapp.util;

import com.groupoffive.listapp.exceptions.UnableToNotifyUserException;
import com.groupoffive.listapp.models.Usuario;

public interface NotificationService {

    void persistToken(Usuario usuario, String firebaseToken);

    void notifyUser(Usuario usuario, String titulo, String mensagem) throws UnableToNotifyUserException;

}
