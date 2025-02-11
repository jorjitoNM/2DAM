package com.hospital_spring.domain.error;

import com.hospital_spring.common.Constantes;

public final class FOREIGN_KEY_ERROR extends DataBaseError {
    public FOREIGN_KEY_ERROR() {
        super(Constantes.FOREIGN_KEY_ERROR);
    }
}
