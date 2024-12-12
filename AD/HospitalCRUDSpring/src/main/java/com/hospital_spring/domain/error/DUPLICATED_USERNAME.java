package com.hospital_spring.domain.error;

import com.hospital_spring.common.Constantes;

public final class DUPLICATED_USERNAME extends DataBaseError {
    public DUPLICATED_USERNAME () {
        super(Constantes.DUPLICATED_USERNAME_ERROR);
    }
}
