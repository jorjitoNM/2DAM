package org.example.backend.common;

public class Constantes {
    public static final String USER = "user";
    public static final String PATH_USER = "/" + USER;
    public static final String ERROR_AL_ENVIAR_EL_CORREO = "Error al enviar el correo: {}";
    public static final String MAIL = "mario.valiente2@educa.madrid.org";
    public static final String ACTIVAR_USUARIO = "Activar Usuario";
    public static final String ACTIVAR_USER = "<html><a href=\"http://localhost:8080/api/registrar/activar?user=";
    public static final String USUARIO_A_HTML = "\">Activar usuario</a></html>";
    public static final String SERVIDOR = "Servidor";
    public static final String API_LOGIN = "/api/login";
    public static final String REFRESH = "/refresh";
    public static final String API_REGISTRAR = "/api/registrar";
    public static final String ID = "/{id}";
    public static final String LIBROS = ID + "/libros";
    public static final String ACTIVAR = "/activar";
    public static final String ACTIVARLO = "Usuario registrado correctamente, revisa tu correo para activarlo";
    public static final String ERROR_AL_ENVIAR_EL_CORREO_PARA_USUARIO = "Error al enviar el correo";
    public static final String API_AUTORES = "api/autores";
    public static final String API_LIBROS = "/api/libros";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String LIBRO_NO_ENCONTRADO = "Libro no encontrado";
    public static final String AUTHORIZATION = "Authorization";
    public static final String COMA = ",";
    public static final String CLAVE_ALEATORIA = "ClaveAleatoria";
    public static final String ALGORITHM = "SHA-512";
    public static final String AUTOR_NO_ENCONTRADO = "Autor no encontrado";
    public static final String ADMIN = "'ADMIN'";
    public static final String USER_ROL = "'USER'";
    public static final String API_LOGIN_PATH = "/api/login/**";
    public static final String API_REGISTRAR_PATH = "/api/registrar/**";
    public static final String BEARER = "Bearer ";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String AUTORES = "autores";
    public static final String LIBROS_JSON = "libros";

    private Constantes() {

    }
}
