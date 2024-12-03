package org.example.appmensajessecretos.domain.service;

import io.vavr.control.Either;
import org.example.appmensajessecretos.dao.DaoMessages;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.example.appmensajessecretos.domain.model.Mensaje;
import org.example.appmensajessecretos.domain.model.MensajePrivado;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.example.appmensajessecretos.domain.validator.ValidateGroup;
import org.example.appmensajessecretos.domain.validator.ValidateMessage;
import org.example.appmensajessecretos.domain.validator.ValidateUser;
import org.example.appmensajessecretos.utilities.security.Asymmetric;
import org.example.appmensajessecretos.utilities.security.Symmetric;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class
MessageService {
    private final DaoMessages dao;
    private final Symmetric symmetric;
    private final ValidateMessage messageValidator;
    private final ValidateUser userValidator;
    private final ValidateGroup groupValidator;
    private final Asymmetric asymmetric;
    private final DaoMessages daoMessages;


    public MessageService(DaoMessages dao, Symmetric symmetric, ValidateMessage messageValidator, ValidateUser userValidator, ValidateGroup groupValidator, Asymmetric asymmetric, DaoMessages daoMessages) {
        this.dao = dao;
        this.symmetric = symmetric;
        this.messageValidator = messageValidator;
        this.userValidator = userValidator;
        this.groupValidator = groupValidator;
        this.asymmetric = asymmetric;
        this.daoMessages = daoMessages;
    }

    public CompletableFuture<Either<Error, Void>> sendMessage(String text, Usuario user, Grupo group) {
        return CompletableFuture.completedFuture(userValidator.validateUserIsLogged(user)
                .flatMap(nada -> groupValidator.validateGroup(group))
                .flatMap(nada -> messageValidator.validateMessage(new Mensaje(text, user.getName(), group.getName())))
                .flatMap(nada2 -> cipherSymmetric(text, user, group))
                .flatMap(cipheredText -> dao.sendMessage(new Mensaje(cipheredText, user.getName(), group.getName()))));
    }

    public CompletableFuture<Either<Error, Void>> sendPrivateMessage(String text, Usuario user, Grupo group) {
        return CompletableFuture.completedFuture(userValidator.validateUserIsLogged(user)
                .flatMap(nada -> groupValidator.validateGroup(group))
                .flatMap(nada -> cipherAsymmetricMessage(text, group, user))
                .flatMap(daoMessages::sendPrivateMessage));
    }

    private Either<Error, String> cipherSymmetric(String text, Usuario user, Grupo group) {
        return asymmetric.getPrivateKey(user)
                .flatMap(userPrivateKey -> asymmetric.decipher(user.getGroupPasswords().get(group.getName()), userPrivateKey))
                .flatMap(groupPassword -> symmetric.cipher(text, groupPassword));
    }

    private Either<Error, MensajePrivado> cipherAsymmetricMessage(String text, Grupo group, Usuario user) {
        String randomKey = asymmetric.getRandomKey();
        return symmetric.cipher(text,randomKey)
                .flatMap(cipheredText -> {
                    List<String> cipheredPasswords = new ArrayList<>();
                    for (Usuario member : group.getMembers()) {
                        Either<Error, String> cipheredPassword = asymmetric.getPublicKey(member)
                                .flatMap(userPublicKey -> asymmetric.cipher(randomKey, userPublicKey));
                        if (cipheredPassword.isLeft())
                            return Either.left(ServiceError.ERROR_ENCRYPTING);
                        else
                            cipheredPasswords.add(cipheredPassword.get());
                    }
                    Map<String, String> keys = new HashMap<>();
                    for (int i = 0; i < group.getMembers().size(); i++) {
                        keys.put(group.getMembers().get(i).getName(), cipheredPasswords.get(i));
                    }
                    return Either.right(new MensajePrivado(cipheredText, LocalDateTime.now(), user.getName(), group.getName(), keys));
                });
    }


    private Either<Error, List<Mensaje>> decipherSymmetricMessages(List<Mensaje> messages, String groupKey) {
        List<Mensaje> decipheredMessages = new ArrayList<>();
        for (Mensaje message : messages) {
            Either<Error, String> either = symmetric.decipher(message.getContent(), groupKey);
            if (either.isLeft())
                return Either.left(ServiceError.ERROR_DECRYPTING);
            else
                decipheredMessages.add(new Mensaje(either.get(), message.getAuthor(), message.getGrupo()));
        }
        return Either.right(decipheredMessages);
    }

    private Either<Error, List<Mensaje>> decipherAsymmetricMessages(List<MensajePrivado> messages, PrivateKey userPrivateKey, Usuario user) {
        List<Mensaje> decipheredMessages = new ArrayList<>();
        for (MensajePrivado message : messages) {
            Either<Error, String> either = asymmetric.decipher(message.getKeys().get(user.getName()), userPrivateKey)
                    .flatMap(messageKey -> symmetric.decipher(message.getContent(), messageKey));
            if (either.isLeft())
                return Either.left(ServiceError.ERROR_DECRYPTING);
            else
                decipheredMessages.add(new Mensaje(either.get(), message.getAuthor(), message.getGrupo()));
        }
        return Either.right(decipheredMessages);
    }

    public CompletableFuture<Either<Error, List<Mensaje>>> getMessages(Grupo group, Usuario user) {
        return CompletableFuture.completedFuture(groupValidator.validateGroup(group)
                .flatMap(nada -> asymmetric.getPrivateKey(user))
                .flatMap(userPrivateKey -> asymmetric.decipher(user.getGroupPasswords().get(group.getName()), userPrivateKey))
                .flatMap(groupPassword -> dao.loadMessages(group)
                        .flatMap(messages -> {
                                    if (messages.isEmpty())
                                        return Either.left(ServiceError.GROUP_HAS_NO_MESSAGES);
                                    else
                                        return decipherSymmetricMessages(messages, groupPassword);
                                }
                        )));
    }


    public CompletableFuture<Either<Error, List<Mensaje>>> getPrivateMessages(Usuario user, Grupo group) {
        return CompletableFuture.completedFuture(groupValidator.validateGroup(group)
                .flatMap(nada -> asymmetric.getPrivateKey(user))
                .flatMap(userPrivateKey -> dao.loadPrivateMessages(group)
                        .flatMap(messages -> {
                                    if (messages.isEmpty())
                                        return Either.left(ServiceError.GROUP_HAS_NO_MESSAGES);
                                    else
                                        return decipherAsymmetricMessages(messages, userPrivateKey,user);
                                }
                        )));
    }

}