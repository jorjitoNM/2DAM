package org.example.appmensajessecretos.dao;

import io.vavr.control.Either;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.example.appmensajessecretos.domain.model.Mensaje;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DaoMessages {

    private final DataBase dataBase;


    public DaoMessages(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public Either<Error,Void> sendMessage(String text, Usuario usuario, Grupo group) {
        return dataBase.loadMessages()
                .flatMap(mensajes -> {
                    try {
                        mensajes.add(new Mensaje(text,LocalDateTime.now(),usuario.getName(),group.getName()));
                        return dataBase.saveMessages(mensajes);
                    } catch (Exception e) {
                        return Either.left(ServiceError.ERROR_SENDING_MESSAGE);
                    }
                });
    }

    public Either<Error,List<Mensaje>> loadMessages(Grupo group) {
        return dataBase.loadMessages()
                .flatMap(mensajes ->
                Either.right(mensajes.stream().filter(m -> m.getGrupo().equals(group.getName())).toList()));
    }
}
