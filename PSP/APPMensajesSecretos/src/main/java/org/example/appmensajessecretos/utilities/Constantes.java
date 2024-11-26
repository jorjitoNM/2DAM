package org.example.appmensajessecretos.utilities;

public class Constantes {
    public static final String RELLENE_CAMPOS = "Complete los campos";
    public static final String USUARIO_MISSING = "El usuario introducido no existe, ¿desea crearlo?";
    public static final String COMFIRMACION = "Comfirmation:";
    public static final String MENSAJE_ELIMINAR_USUARIO = "¿Esta seguro de que quiere eliminar este usuario?";
    public static final String JOINED_GROUP = "Se ha unido al grupo";
    public static final String ERROR_JOINING_GROUP = "No se ha podido unir al grupo";
    public static final String CONTRASEÑA_INCORRECTA = "Contraseña incorrecta";
    public static final String GRUPO_NO_EXISTE = "El grupo no existe, si quiere puede crearlo";
    public static final String NOT_LOGGED = "Inicie sesion primero";
    public static final String GROUP_CREATED = "Grupo creado";
    public static final String MEMBER_DELETED = "El usuario ha sido eliminado del grupo";
    public static final String ERROR_SENDING_MESSAGE = "No se puede mandar el mensaje";
    public static final String LOGGED_IN = "Logged in";
    public static final String DATABASE_FAILED = "El sistema no ha podido completar su accion, intentelo de nuevo";
    public static final String DATABASE_CONECCTION_FAILED = "No se ha podido conectar con las base de datos";
    public static final String GROUP_IS_PRIVATE = "No se puede unir a un grupo privado, debe recibir una invitacion desde dentro";
    public static final String USER_NOT_IN_GROUP = "El usuario seleccionado no se encuentra en este grupo";
    public static final String GROUP_ALREADY_EXIST = "Ya existe un grupo con ese nombre, utilice otro distinto";
    public static final String USER_NOT_IN_GROUPS = "No tienes chats disponibles";
    public static final String MESSAGE_SENT = "Mensaje enviado con exito";
    public static final String USER_INVITED = "Se ha invitado al usuario al grupo seleccionado";
    public static final String UNEXPECTED_ERROR = "Ha ocurrido un error inesperado";
    public static final String USER_ALREADY_EXISTS = "Su nombre de usuario ya esta empleado por otro usuario, introduzca uno distinto";
    public static final String INFO = "Information";
    public static final String ACTION_COMPLETED = "Accion completada:";
    public static final String ALREADY_IN_GROUP = "No se puede unir 2 veces al mismo grupo";
    public static final String ERROR_COMPLETING_TASK = "No se ha podido completar su peticion, intentlo de nuevo";



    //Symmetric
    public static final String CIPHER_ALGORITHM = "PBKDF2WithHmacSHA256";
    public static final String CIPHER_TRANSFORMATION = "AES/GCM/noPadding";
    public static final String AES = "AES";
    public static final String ERROR_ENCRYPTING = "No se han podido encriptar sus mensajes, asegurese de que la contraseña que ha introducido la que desea e intentelo de nuevo";
    public static final String ERROR_DECRYPTING = "No se han podido desencriptar sus mensajes, asegurese de que la contraseña introducida es su contraseña de encriptacion";
    public static final int ITERATIONS = 65536;
    public static final int CIPHER_KEY_LENGTH = 256;

    //Asymmetric
    public static final String SERVER_PRIVATE_KEY = "keyStorePassword";
    public static final String KEY_STORE_PATH = "keyStorePath";
    public static final String SERVER = "server";
    public static final String KEY_STORE_TYPE = "JKS";
    public static final String GENERATING_CERTIFICATE_COMMON_NAME = "CN=Test Certificate";


    //Ficheros
    public static final String RUTA_CONFIG_PROPERTIES = "config/config.properties";
    public static final String PATH_USUARIOS = "pathUsuarios";
    public static final String PATH_GRUPOS = "pathGrupos";
    public static final String PATH_MENSAJES = "pathMensajes";

    private Constantes () {}
}
