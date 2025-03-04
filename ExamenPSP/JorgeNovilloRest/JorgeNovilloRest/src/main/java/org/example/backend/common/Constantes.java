package org.example.backend.common;

public class Constantes {
    public static final String LOGIN_URL = "/login";
    public static final String CHECK_PASSWORD_URL = "/password";
    public static final String USER_ROLE = "user";
    public static final String ADMIN_ROLE = "admin";
    public static final String USER_NOT_FOUND = "no se ha encontrado al usuario";
    public static final String SERVIDOR = "examen";
    public static final String NAME = "name";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String PETICION_INCOMPLETA = "Peticion incompleta, envie un token";
    public static final String COSAS_URL = "/cosas";
    public static final String GET_ALL = "/getAll";
    public static final String GET_COSAS = "/getMisCosas";
    public static final String NO_TIENES_COSAS = "No tienes cosas, crea una para poder visualizarlas";
    public static final String EMPLEADO = "/empleados";
    public static final String ADD_USER_URL = "/add";
    public static final String UPDATE = "/update";
    public static final String NO_ES_TU_COSA = "no puedes actualizar una cosa que no es tuya";

    private Constantes() {

    }
}
