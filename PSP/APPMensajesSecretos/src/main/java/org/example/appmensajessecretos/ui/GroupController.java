package org.example.appmensajessecretos.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.appmensajessecretos.domain.error.DataBaseError;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.modelo.Grupo;
import org.example.appmensajessecretos.domain.modelo.Usuario;
import org.example.appmensajessecretos.domain.servicio.GroupService;
import org.example.appmensajessecretos.domain.servicio.MessageService;
import org.example.appmensajessecretos.domain.servicio.UserService;
import org.example.appmensajessecretos.utilities.Constantes;
import org.springframework.stereotype.Component;

@Component
public class GroupController {

    private final UserService userService;
    private final MessageService messageService;
    private final GroupService groupService;
    private Usuario usuario;


    @FXML
    private TextField userName;
    @FXML
    private TextField userPassword;
    @FXML
    private Label logInError;


    @FXML
    private TextField groupName;
    @FXML
    private TextField groupPassword;
    @FXML
    private Label joinGroupError;


    @FXML
    private TextField groupNameDelete;
    @FXML
    private TextField userNameDelete;
    @FXML
    private Label deleteError;


    @FXML
    private TextField createGroupName;
    @FXML
    private TextField createGroupPassword;
    @FXML
    private Label createGroupError;
    @FXML
    private CheckBox isPrivate;


    @FXML
    private ListView<Grupo> myChats;
    @FXML
    private Label userInfoError;


    @FXML
    private TextArea mensaje;
    @FXML
    private Label sendMessageError;


    @FXML
    private ListView<String> groupChats;
    @FXML
    private Label chatError;


    @FXML
    private ListView<Usuario> usuarios;
    @FXML
    private Label inviteUserError;


    public GroupController(UserService userService, MessageService messageService, GroupService groupService) {
        this.userService = userService;
        this.messageService = messageService;
        this.groupService = groupService;
        usuario = null;
    }


    private boolean checkLogged() {
        if (usuario == null) {
            logInError.setText(Constantes.NOT_LOGGED);
            return false;
        } else
            return true;
    }


    public void iniciarSesion() {
        if (userName.getText().isEmpty() || userPassword.getText().isEmpty())
            logInError.setText(Constantes.RELLENE_CAMPOS);
        else
            userService.logIn(new Usuario(userName.getText(), userPassword.getText()))
                    .peek(ok -> {
                        usuario = ok;
                        actualizarUserInfo();
                        logInError.setText(Constantes.LOGGED_IN);
                        loadUsers();
                    })
                    .peekLeft(this::showLogInError);
    }

    private void showLogInError(Error error) {
        String errorMessage = "";
        switch (error) {
            case DataBaseError e when e == DataBaseError.ERROR_IN_FETCH -> errorMessage = Constantes.DATABASE_FAILED;
            case ServiceError e when e == ServiceError.USER_NOT_FOUND -> createUser();
            case DataInputError e when e == DataInputError.INCORRECT_PASSWORD ->
                    errorMessage = Constantes.CONTRASEÑA_INCORRECTA;
            default -> errorMessage = Constantes.LOGIN_FAILED;
        }
        logInError.setText(errorMessage);
    }

    private void createUser() {
        if (alertComfirmation(Constantes.USUARIO_MISSING)) {
            userService.addUser(new Usuario(userName.getText(), userPassword.getText()))
                    .peekLeft(error -> {
                        if (error == DataBaseError.ERROR_IN_FETCH) {
                            logInError.setText(Constantes.DATABASE_FAILED);
                        }
                    });
            iniciarSesion();
        }
    }


    public void createGroup() {
        if (checkLogged()) {
            if (createGroupName.getText().isEmpty() || createGroupPassword.getText().isEmpty())
                createGroupError.setText(Constantes.RELLENE_CAMPOS);
            else {
                Grupo grupo = new Grupo(createGroupName.getText(), createGroupPassword.getText(), isPrivate.isSelected());
                groupService.createGroup(grupo, usuario)
                        .peek(ok -> {
                            createGroupError.setText(Constantes.GROUP_CREATED);
                            actualizarUserInfo();
                        })
                        .peekLeft(this::showCreateGroupError);
            }
        }
    }

    private void showCreateGroupError(Error error) {
        String errorMessage = "";
        switch (error) {
            case DataBaseError e when e == DataBaseError.ERROR_IN_FETCH -> errorMessage = Constantes.DATABASE_FAILED;
            case ServiceError e when e == ServiceError.GROUP_ALREADY_EXISTS ->
                    errorMessage = Constantes.GROUP_ALREADY_EXIST;
            default -> errorMessage = Constantes.ERROR_CREATING_GROUP;
        }
        createGroupError.setText(errorMessage);
    }


    public void joinGroup() {
        if (checkLogged()) {
            if (groupName.getText().isEmpty() || groupPassword.getText().isEmpty())
                joinGroupError.setText(Constantes.RELLENE_CAMPOS);
            else {
                Grupo grupo = new Grupo(groupName.getText(), groupPassword.getText(), isPrivate.isSelected());
                groupService.joinGroup(usuario, grupo).peek(ok -> {
                    joinGroupError.setText(Constantes.JOINED_GROUP);
                    actualizarUserInfo();
                }).peekLeft(this::showJoinGroupError);
            }
        }
    }

    private void showJoinGroupError(Error error) {
        String errorMessage;
        switch (error) {
            case DataBaseError e when e == DataBaseError.ACTION_FAILED -> errorMessage = Constantes.DATABASE_FAILED;
            case DataInputError e when e == DataInputError.GROUP_IS_PRIVATE ->
                    errorMessage = Constantes.GROUP_IS_PRIVATE;
            case ServiceError e when e == ServiceError.GROUP_NOT_FOUND -> errorMessage = Constantes.GRUPO_NO_EXISTE;
            default -> errorMessage = Constantes.ERROR_JOINING_GROUP;
        }
        joinGroupError.setText(errorMessage);
    }


    public void comfirmDelete() {
        if (checkLogged()) {
            if (userNameDelete.getText().isEmpty() || groupNameDelete.getText().isEmpty())
                deleteError.setText(Constantes.RELLENE_CAMPOS);
        } else {
            if (alertComfirmation(Constantes.MENSAJE_ELIMINAR_USUARIO)) {
                groupService.deleteMember(userNameDelete.getText(), groupNameDelete.getText())
                        .peek(ok -> {
                            deleteError.setText(Constantes.MEMBER_DELETED);
                            actualizarUserInfo();
                        })
                        .peekLeft(this::showDeleteGroupError);
            }
        }
    }

    private void showDeleteGroupError(Error error) {
        String errorMessage = "";
        switch (error) {
            case DataBaseError e when e == DataBaseError.ERROR_IN_FETCH -> errorMessage = Constantes.DATABASE_FAILED;
            case ServiceError e -> {
                switch (e) {
                    case GROUP_NOT_FOUND -> errorMessage = Constantes.GRUPO_NO_EXISTE;
                    case NOT_IN_GROUP -> errorMessage = Constantes.USER_NOT_IN_GROUP;
                }
            }
            default -> errorMessage = Constantes.ERROR_DELETING_USER;
        }
        deleteError.setText(errorMessage);
    }


    public void sendGroupMessage() {
        if (checkLogged()) {
            if (myChats.getSelectionModel().isEmpty() || mensaje.getText().isBlank())
                sendMessageError.setText(Constantes.RELLENE_CAMPOS);
            else
                messageService.sendGroupMessages(mensaje.getText(), usuario, myChats.getSelectionModel().getSelectedItem())
                        .peek(ok -> {
                            loadUserGroupChats();
                            sendMessageError.setText(Constantes.MESSAGE_SENT);
                        })
                        .peekLeft(this::showSendingMessageError);
        }
    }

    private void showSendingMessageError(Error error) {
        String errorMessage = "";
        switch (error) {
            case DataBaseError e when e == DataBaseError.ERROR_IN_FETCH -> errorMessage = Constantes.DATABASE_FAILED;
            case ServiceError e -> {
                switch (e) {
                    case GROUP_NOT_FOUND -> errorMessage = Constantes.GRUPO_NO_EXISTE;
                    case ERROR_SENDING_MESSAGE -> errorMessage = Constantes.ERROR_SENDING_MESSAGE;
                }
            }
            default -> errorMessage = Constantes.ERROR_SENDING_MESSAGE;
        }
        sendMessageError.setText(errorMessage);
    }


    public void inviteUser() {
        if (checkLogged()) {
            if (myChats.getSelectionModel().isEmpty() || usuarios.getSelectionModel().isEmpty())
                sendMessageError.setText(Constantes.RELLENE_CAMPOS);
            else {
                groupService.inviteUser(myChats.getSelectionModel().getSelectedItem(), usuarios.getSelectionModel().getSelectedItems())
                        .peek(ok -> inviteUserError.setText(Constantes.USER_INVITED))
                        .peekLeft(error -> {
                            switch (error) {
                                case ServiceError e when e == ServiceError.GROUP_NOT_FOUND ->
                                        inviteUserError.setText(Constantes.GRUPO_NO_EXISTE);
                                case DataBaseError e when e == DataBaseError.ERROR_IN_FETCH ->
                                        inviteUserError.setText(Constantes.DATABASE_FAILED);
                                case DataInputError e when e == DataInputError.GROUP_IS_PRIVATE ->
                                        inviteUserError.setText(Constantes.ERROR_INVITING_USER);
                                default -> inviteUserError.setText(Constantes.ERROR_INVITING_USER);
                            }
                        });
            }
        }
    }


    private void actualizarUserInfo() {
        myChats.getItems().clear();
        groupService.getGroups(usuario).peek(ok -> {
            myChats.getItems().addAll(ok);
        }).peekLeft(error -> {
            if (error == DataBaseError.ERROR_IN_FETCH)
                userInfoError.setText(Constantes.DATABASE_FAILED);
            else if (error == ServiceError.NOT_IN_GROUP)
                userInfoError.setText(Constantes.USER_NOT_IN_GROUPS);
            else
                userInfoError.setText(Constantes.ERROR_LOADING_USER_CHATS);
        });
    }


    public void loadUsers() {
        usuarios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        usuarios.getItems().clear();
        userService.loadUsers(usuario).peek(ok ->
                        usuarios.getItems().addAll(ok))
                .peekLeft(error -> {
                    if (error instanceof DataBaseError)
                        inviteUserError.setText(Constantes.DATABASE_FAILED);
                    else
                        inviteUserError.setText(Constantes.ERROR_LOADING_USER_CHATS);
                });
    }


    private boolean alertComfirmation(String message) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle(Constantes.COMFIRMACION);
        confirmation.setHeaderText(message);
        confirmation.showAndWait();
        confirmation.close();
        return confirmation.getResult().equals(ButtonType.OK);
    }


    public void loadGroupChats() {
        if (!myChats.getSelectionModel().isEmpty())
            loadUserGroupChats();
    }

    public void loadUserGroupChats() {
        groupChats.getItems().clear();
        messageService.getMessages(myChats.getSelectionModel().getSelectedItem()).peek(ok -> ok.forEach(m ->
                groupChats.getItems().add(m.toString())
        )).peekLeft(error -> {
            if (error instanceof DataBaseError)
                chatError.setText(Constantes.DATABASE_FAILED);
            else
                chatError.setText(Constantes.ERROR_LOADING_USER_CHATS);
        });
    }
}