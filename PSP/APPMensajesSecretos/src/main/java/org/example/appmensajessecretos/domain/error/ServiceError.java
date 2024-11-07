package org.example.appmensajessecretos.domain.error;

public enum ServiceError implements Error{
    USER_NOT_FOUND,
    GROUP_NOT_FOUND,
    GROUP_ALREADY_EXISTS,
    NOT_IN_GROUPS,
    NOT_IN_GROUP,
    ERROR_SENDING_MESSAGE,
    ERROR_JOINING_GROUP, USER_ALREADY_EXIST,
    ERROR_ENCRYPTING, ERROR_DECRYPTING, ALREADY_IN_GROUP,
}
