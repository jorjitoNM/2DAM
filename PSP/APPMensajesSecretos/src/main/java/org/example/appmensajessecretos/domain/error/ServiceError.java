package org.example.appmensajessecretos.domain.error;

public enum ServiceError implements Error{
    GROUP_NOT_FOUND,
    NOT_IN_GROUPS,
    GROUP_ALREADY_EXISTS,
    NOT_IN_GROUP,
    USER_NOT_FOUND,
    ERROR_SENDING_MESSAGE,
    ERROR_JOINING_GROUP,
}
