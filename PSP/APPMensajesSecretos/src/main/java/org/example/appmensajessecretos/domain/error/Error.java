package org.example.appmensajessecretos.domain.error;

public sealed interface Error
        permits DataInputError, DataBaseError, ServiceError{}
