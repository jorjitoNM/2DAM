package org.example.appmensajessecretos.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.appmensajessecretos.domain.error.DataBaseError;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.example.appmensajessecretos.domain.model.Usuario;
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
    private final Alert errorAlert;
    private final Alert infoAlert;


    @FXML
    private TextField userName;
    @FXML
    private TextField userPassword;


    @FXML
    private TextField groupName;
    @FXML
    private TextField groupPassword;


    @FXML
    private TextField groupNameDelete;
    @FXML
    private TextField userNameDelete;


    @FXML
    private TextField createGroupName;
    @FXML
    private TextField createGroupPassword;
    @FXML
    private CheckBox isPrivate;


    @FXML
    private ListView<Grupo> myChats;


    @FXML
    private TextArea mensaje;
    @FXML
    private TextField encryptPassword;


    @FXML
    private ListView<String> groupChats;


    @FXML
    private ListView<Usuario> usuarios;


    public GroupController(UserService userService, MessageService messageService, GroupService groupService) {
        this.userService = userService;
        this.messageService = messageService;
        this.groupService = groupService;
        usuario = null;
        errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Error");
        infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle(Constantes.INFO);
        infoAlert.setHeaderText(Constantes.ACTION_COMPLETED);
    }


    private boolean checkLogged() {
        if (usuario == null) {
            showError(DataInputError.NOT_LOGGED);
            return false;
        } else
            return true;
    }


    public void iniciarSesion() {
        if (userName.getText().isEmpty() || userPassword.getText().isEmpty())
            showError(DataInputError.EMPTY_FIELDS);
        else
            userService.logIn(new Usuario(userName.getText(), userPassword.getText()))
                    .peek(ok -> {
                        usuario = ok;
                        actualizarUserInfo();
                        showInfo(Constantes.LOGGED_IN);
                        loadUsers();
                    })
                    .peekLeft(this::showError);
    }

    public void showInfo (String info) {
        infoAlert.setTitle(info);
        infoAlert.showAndWait();
    }


    private void showError (Error error) {
        String errorMessage = "";
        switch (error) {
            case DataBaseError e -> {
               if (e == DataBaseError.ACTION_FAILED)
                   errorMessage = Constantes.DATABASE_FAILED;
               else
                   errorMessage = Constantes.DATABASE_CONECCTION_FAILED;
            }
            case ServiceError e -> {
                switch (e) {
                    case GROUP_NOT_FOUND -> errorMessage = Constantes.GRUPO_NO_EXISTE;
                    case NOT_IN_GROUP -> errorMessage = Constantes.USER_NOT_IN_GROUP;
                    case NOT_IN_GROUPS -> errorMessage = Constantes.USER_NOT_IN_GROUPS;
                    case USER_NOT_FOUND -> createUser();
                    case GROUP_ALREADY_EXISTS -> errorMessage = Constantes.GROUP_ALREADY_EXIST;
                    case ERROR_SENDING_MESSAGE -> errorMessage = Constantes.ERROR_SENDING_MESSAGE;
                    case ERROR_JOINING_GROUP -> errorMessage = Constantes.ERROR_JOINING_GROUP;
                    case USER_ALREADY_EXIST -> errorMessage = Constantes.USER_ALREADY_EXISTS;
                }
            }
            case DataInputError e -> {
                switch (e) {
                    case INCORRECT_PASSWORD -> errorMessage = Constantes.CONTRASEÑA_INCORRECTA;
                    case GROUP_IS_PRIVATE -> errorMessage = Constantes.GROUP_IS_PRIVATE;
                    case NOT_LOGGED -> errorMessage = Constantes.NOT_LOGGED;
                    case EMPTY_FIELDS -> errorMessage = Constantes.RELLENE_CAMPOS;
                }
            }
            default -> errorMessage = Constantes.UNEXPECTED_ERROR;
        }
        errorAlert.setContentText(errorMessage);
    }

    private void createUser() {
        if (alertComfirmation(Constantes.USUARIO_MISSING)) {
            userService.addUser(new Usuario(userName.getText(), userPassword.getText()))
                    .peek(ok -> iniciarSesion())
                    .peekLeft(this::showError);
        }
    }


    public void createGroup() {
        if (checkLogged()) {
            if (createGroupName.getText().isEmpty() || createGroupPassword.getText().isEmpty())
                showError(DataInputError.EMPTY_FIELDS);
            else {
                Grupo grupo = new Grupo(createGroupName.getText(), createGroupPassword.getText(), isPrivate.isSelected());
                groupService.createGroup(grupo, usuario)
                        .peek(ok -> {
                            showInfo(Constantes.GROUP_CREATED);
                            actualizarUserInfo();
                        })
                        .peekLeft(this::showError);
            }
        }
    }


    public void joinGroup() {
        if (checkLogged()) {
            if (groupName.getText().isEmpty() || groupPassword.getText().isEmpty())
                showError(DataInputError.EMPTY_FIELDS);
            else {
                Grupo grupo = new Grupo(groupName.getText(), groupPassword.getText(), isPrivate.isSelected());
                groupService.joinGroup(usuario, grupo).peek(ok -> {
                    showInfo(Constantes.JOINED_GROUP);
                    actualizarUserInfo();
                }).peekLeft(this::showError);
            }
        }
    }


    public void comfirmDelete() {
        if (checkLogged()) {
            if (userNameDelete.getText().isEmpty() || groupNameDelete.getText().isEmpty())
                showError(DataInputError.EMPTY_FIELDS);
        } else {
            if (alertComfirmation(Constantes.MENSAJE_ELIMINAR_USUARIO)) {
                groupService.deleteMember(userNameDelete.getText(), groupNameDelete.getText())
                        .peek(ok -> {
                            showInfo(Constantes.MEMBER_DELETED);
                            actualizarUserInfo();
                        })
                        .peekLeft(this::showError);
            }
        }
    }


    public void sendMessage() {
        if (checkLogged()) {
            if (myChats.getSelectionModel().isEmpty() || mensaje.getText().isBlank())
               showError(DataInputError.EMPTY_FIELDS);
            else
                messageService.sendMessages(mensaje.getText(), usuario, myChats.getSelectionModel().getSelectedItem(),encryptPassword.getText())
                        .peek(ok -> {
                            loadUserGroupChats();
                            showInfo(Constantes.MESSAGE_SENT);
                        })
                        .peekLeft(this::showError);
        }
    }


    public void inviteUser() {
        if (checkLogged()) {
            if (myChats.getSelectionModel().isEmpty() || usuarios.getSelectionModel().isEmpty())
                showError(DataInputError.EMPTY_FIELDS);
            else {
                groupService.inviteUser(myChats.getSelectionModel().getSelectedItem(), usuarios.getSelectionModel().getSelectedItems())
                        .peek(ok -> showInfo(Constantes.USER_INVITED))
                        .peekLeft(this::showError);
            }
        }
    }


    private void actualizarUserInfo() {
        myChats.getItems().clear();
        groupChats.getItems().clear();
        groupService.getGroups(usuario).peek(ok -> {
            myChats.getItems().addAll(ok);
            loadGroupChats();
        }).peekLeft(this::showError);
    }


    public void loadUsers() {
        usuarios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        usuarios.getItems().clear();
        userService.loadUsers(usuario).peek(ok ->
                        usuarios.getItems().addAll(ok))
                .peekLeft(this::showError);
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
        messageService.getMessages(myChats.getSelectionModel().getSelectedItem(),encryptPassword.getText()).peek(ok -> ok.forEach(m ->
                groupChats.getItems().add(m.toString())
        )).peekLeft(this::showError);
    }
}