package org.example.appmensajessecretos.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.appmensajessecretos.domain.error.DataBaseError;
import org.example.appmensajessecretos.domain.error.DataInputError;
import org.example.appmensajessecretos.domain.error.Error;
import org.example.appmensajessecretos.domain.error.ServiceError;
import org.example.appmensajessecretos.domain.model.Grupo;
import org.example.appmensajessecretos.domain.model.Usuario;
import org.example.appmensajessecretos.domain.service.GroupService;
import org.example.appmensajessecretos.domain.service.MessageService;
import org.example.appmensajessecretos.domain.service.UserService;
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
    private TextArea message;
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
    }


    public void logIn() {
        userService.logIn(new Usuario(userName.getText(), userPassword.getText()))
                .peek(ok -> {
                    usuario = ok;
                    updateUserInfo();
                    showInfo(Constantes.LOGGED_IN);
                    loadUsers();
                })
                .peekLeft(this::showError);
    }

    private void createUser() {
        if (confirmationAlert(Constantes.USUARIO_MISSING)) {
            userService.addUser(new Usuario(userName.getText(), userPassword.getText()))
                    .peek(ok -> logIn())
                    .peekLeft(this::showError);
        }
    }

    public void showInfo(String info) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle(Constantes.INFO);
        infoAlert.setHeaderText(Constantes.ACTION_COMPLETED);
        infoAlert.setContentText(info);
        infoAlert.showAndWait();
    }


    private void showError(Error error) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Error");
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
                    case ERROR_ENCRYPTING -> errorMessage = Constantes.ERROR_ENCRYPTING;
                    case ERROR_DECRYPTING -> errorMessage = Constantes.ERROR_DECRYPTING;
                    case ALREADY_IN_GROUP -> errorMessage = Constantes.ALREADY_IN_GROUP;
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
        errorAlert.showAndWait();
    }


    public void createGroup() {
        Grupo grupo = new Grupo(createGroupName.getText(), createGroupPassword.getText(), isPrivate.isSelected());
        groupService.createGroup(grupo, usuario)
                .peek(ok -> {
                    showInfo(Constantes.GROUP_CREATED);
                    updateUserInfo();
                })
                .peekLeft(this::showError);
    }


    public void joinGroup() {
        Grupo grupo = new Grupo(groupName.getText(), groupPassword.getText(), isPrivate.isSelected());
        groupService.joinGroup(usuario, grupo).peek(ok -> {
            showInfo(Constantes.JOINED_GROUP);
            updateUserInfo();
        }).peekLeft(this::showError);
    }


    public void confirmDelete() {
        if (confirmationAlert(Constantes.MENSAJE_ELIMINAR_USUARIO)) {
            groupService.deleteMember(userNameDelete.getText(), groupNameDelete.getText(), usuario)
                    .peek(ok -> {
                        showInfo(Constantes.MEMBER_DELETED);
                        updateUserInfo();
                    })
                    .peekLeft(this::showError);
        }
    }


    public void sendMessage() {
        messageService.sendMessage(message.getText(), usuario, myChats.getSelectionModel().getSelectedItem(), encryptPassword.getText())
                .peek(ok -> {
                    loadUserGroupChats();
                    showInfo(Constantes.MESSAGE_SENT);
                })
                .peekLeft(this::showError);
    }


    public void inviteUser() {
        groupService.inviteUser(myChats.getSelectionModel().getSelectedItem(), usuarios.getSelectionModel().getSelectedItems(), usuario)
                .peek(ok -> showInfo(Constantes.USER_INVITED))
                .peekLeft(this::showError);
    }


    private void updateUserInfo() {
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


    private boolean confirmationAlert(String message) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle(Constantes.COMFIRMACION);
        confirmation.setHeaderText(message);
        confirmation.showAndWait();
        confirmation.close();
        return confirmation.getResult().equals(ButtonType.OK);
    }


    public void loadGroupChats() {
        groupChats.getItems().clear();
        if (!myChats.getSelectionModel().isEmpty())
            loadUserGroupChats();
    }

    public void loadUserGroupChats() {
        messageService.getMessages(myChats.getSelectionModel().getSelectedItem(), encryptPassword.getText()).peek(ok -> ok.forEach(m ->
                groupChats.getItems().add(m.toString())
        )).peekLeft(this::showError);
    }
}